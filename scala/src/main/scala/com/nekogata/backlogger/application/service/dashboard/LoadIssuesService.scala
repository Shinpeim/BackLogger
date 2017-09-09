package com.nekogata.backlogger.application.service.dashboard

import com.nekogata.backlogger.application.command.dashboard.LoadIssuesCommand
import com.nekogata.backlogger.domain.setting.{SettingRepository, UserIdRepository}
import com.nekogata.backlogger.infrastructure.api_client.BackLogApiClient
import com.nekogata.backlogger.infrastructure.setting.{SettingRepositoryImpl, UserIdRepositoryImpl}

class LoadIssuesService {
  import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

  val settingRepository: SettingRepository =  new SettingRepositoryImpl
  val userIdRepository: UserIdRepository =  new UserIdRepositoryImpl

  def execute(command: LoadIssuesCommand):Unit = {
    println(command.projectIdAsOption)

    command.projectIdAsOption.foreach(projectId => {
      loadIssues(projectId)
    })

  }

  private def loadIssues(projectId: Int):Unit = {
    val userId = userIdRepository.get()

    client.getIssuesOf(projectId, userId).map(json => {
      val summaries = json.map(i => i.summary.asInstanceOf[String])
      println(summaries)
    })
  }


  private def client() =  {
    val setting = settingRepository.get
    new BackLogApiClient(setting.spaceName, setting.apiKey)
  }
}

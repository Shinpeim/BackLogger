package com.nekogata.backlogger.application.service.dashboard

import com.nekogata.backlogger.application.command.dashboard.LoadIssuesCommand
import com.nekogata.backlogger.domain.issue._
import com.nekogata.backlogger.domain.setting.{SettingRepository, UserIdRepository}
import com.nekogata.backlogger.infrastructure.api_client.BackLogApiClient
import com.nekogata.backlogger.infrastructure.issue.IssueRepositoryImpl
import com.nekogata.backlogger.infrastructure.setting.{SettingRepositoryImpl, UserIdRepositoryImpl}
import com.nekogata.backlogger.js_exports.events.IssueEvents

import scala.scalajs.js

class LoadIssuesService {
  import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

  val settingRepository: SettingRepository =  new SettingRepositoryImpl
  val userIdRepository: UserIdRepository =  new UserIdRepositoryImpl
  val issueRepository: IssueRepository = new IssueRepositoryImpl

  def execute(command: LoadIssuesCommand):Unit = {
    println(command.projectIdAsOption)

    command.projectIdAsOption.foreach(projectId => {
      loadIssues(projectId)
    })

  }

  private def loadIssues(projectId: Int):Unit = {
    val userId = userIdRepository.get()

    client.getIssuesOf(projectId, userId).map(json => {
      val issues = json.map(jsonToIssue)
      issueRepository.replaceAllOf(projectId, issues)
      IssueEvents.loaded.fire()
    })
  }


  private def client() =  {
    val setting = settingRepository.get
    new BackLogApiClient(setting.spaceName, setting.apiKey)
  }

  private def jsonToIssue(json: js.Dynamic): Issue = {
    val id = json.id.asInstanceOf[Int]
    val key = json.issueKey.asInstanceOf[String]
    val summary = json.summary.asInstanceOf[String]
    val status = json.status.id.asInstanceOf[Int] match {
      case 1 => Untreated
      case 2 => Processing
      case 3 => Processed
    }

    Issue(id, key, summary, status)
  }
}

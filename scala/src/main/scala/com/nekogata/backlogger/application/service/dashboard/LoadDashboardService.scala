package com.nekogata.backlogger.application.service.dashboard

import com.nekogata.backlogger.application.command.dashboard.LoadDashboardCommand
import com.nekogata.backlogger.domain.setting.{SettingRepository, UserIdRepository}
import com.nekogata.backlogger.infrastructure.api_client.BackLogApiClient
import com.nekogata.backlogger.infrastructure.setting.{SettingRepositoryImpl, UserIdRepositoryImpl}

import scala.concurrent.Future

class LoadDashboardService {
  val settingRepository: SettingRepository =  new SettingRepositoryImpl
  val userIdRepository: UserIdRepository = new UserIdRepositoryImpl

  def execute(command: LoadDashboardCommand) = {
    loadBacklogConfiguration
  }

  private def loadBacklogConfiguration():Future[Unit] = {
    import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

    val setting = settingRepository.get

    val client = new BackLogApiClient(setting.spaceName, setting.apiKey)
    client.getMyself.map(json=> {
      userIdRepository.store(json.userId.asInstanceOf[String])
    })
  }
}

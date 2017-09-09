package com.nekogata.backlogger.application.service.dashboard

import com.nekogata.backlogger.application.command.dashboard.InitializeDashboardCommand
import com.nekogata.backlogger.domain.project.{Project, ProjectRepository}
import com.nekogata.backlogger.domain.setting.{SettingRepository, UserIdRepository}
import com.nekogata.backlogger.infrastructure.api_client.BackLogApiClient
import com.nekogata.backlogger.infrastructure.project.ProjectRepositoryImpl
import com.nekogata.backlogger.infrastructure.setting.{SettingRepositoryImpl, UserIdRepositoryImpl}
import com.nekogata.backlogger.js_exports.events.DashboardEvents

import scala.collection.mutable
import scala.concurrent.Future
import scala.scalajs.js

class InitializeDashboardService {
  import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

  val settingRepository: SettingRepository =  new SettingRepositoryImpl
  val userIdRepository: UserIdRepository = new UserIdRepositoryImpl
  val projectRepository: ProjectRepository = new ProjectRepositoryImpl

  def execute(command: InitializeDashboardCommand):Unit = {
    for {
      _ <- loadUserId()
      _ <- loadProjects()
    } {
      DashboardEvents.initialized.fire()
    }
  }

  private def loadUserId():Future[Unit] = {
    client.getMyself.map(json => {
      userIdRepository.store(json.id.asInstanceOf[Int])
    })
  }

  private def loadProjects(): Future[Unit] = {
    client.getProjects.map(json => {
      val projects = json.map(p => {
        Project(
          p.id.asInstanceOf[Int],
          p.name.asInstanceOf[String],
          p.projectKey.asInstanceOf[String]
        )
      })

      projectRepository.replaceAll(projects)
    })
  }

  private def client() =  {
    val setting = settingRepository.get
    new BackLogApiClient(setting.spaceName, setting.apiKey)
  }
}

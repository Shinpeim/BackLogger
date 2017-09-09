package com.nekogata.backlogger.application.command.dashboard

import com.nekogata.backlogger.application.service.dashboard.InitializeDashboardService

class InitializeDashboardCommand {
  def execute(): Unit = {
    val service = new InitializeDashboardService
    service.execute(this)
  }
}

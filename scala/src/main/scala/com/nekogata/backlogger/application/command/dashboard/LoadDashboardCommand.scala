package com.nekogata.backlogger.application.command.dashboard

import com.nekogata.backlogger.application.service.dashboard.LoadDashboardService

class LoadDashboardCommand {
  def execute(): Unit = {
    val service = new LoadDashboardService
    service.execute(this)
  }
}

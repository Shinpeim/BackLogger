package com.nekogata.backlogger.application.command.setting

import com.nekogata.backlogger.application.service.setting.SaveSettingService

trait SaveSettingCommand {
  var apiKey: String

  def execute(): Unit = {
    val service = new SaveSettingService
    service.execute(this)
  }
}

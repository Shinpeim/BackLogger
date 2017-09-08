package com.nekogata.backlogger.js_exports.commands.setting

import com.nekogata.backlogger.application.command.setting.SaveSetting

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("SaveSettingCommand")
class SaveSettingImpl extends SaveSetting {
  @JSExport
  override def save(apiKey: String): Unit = super.save(apiKey)
}

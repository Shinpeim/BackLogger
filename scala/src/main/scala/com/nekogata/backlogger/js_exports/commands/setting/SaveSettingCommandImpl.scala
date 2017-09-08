package com.nekogata.backlogger.js_exports.commands.setting

import com.nekogata.backlogger.application.command.setting.SaveSettingCommand

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("SaveSettingCommand")
class SaveSettingCommandImpl extends SaveSettingCommand {
  @JSExport
  override var apiKey: String = ""

  @JSExport
  override def execute(): Unit = super.execute()
}

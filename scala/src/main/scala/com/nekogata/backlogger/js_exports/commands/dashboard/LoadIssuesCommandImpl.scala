package com.nekogata.backlogger.js_exports.commands.dashboard

import com.nekogata.backlogger.application.command.dashboard.LoadIssuesCommand

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("LoadIssuesCommand")
class LoadIssuesCommandImpl extends LoadIssuesCommand {
  @JSExport
  override var projectId: js.UndefOr[Int] = js.undefined

  @JSExport
  override def execute(): Unit = super.execute()
}

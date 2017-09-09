package com.nekogata.backlogger.js_exports.commands.dashboard

import com.nekogata.backlogger.application.command.dashboard.{MakeIssueStatusAsProcessedCommand, MakeIssueStatusAsProcessingCommand}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("MakeIssueStatusAsProcessedCommand")
class MakeIssueStatusAsProcessedCommandImpl extends MakeIssueStatusAsProcessedCommand {
  @JSExport
  override var projectId: js.UndefOr[Int] = js.undefined

  @JSExport
  override var issueId: js.UndefOr[Int] = js.undefined

  @JSExport
  override def execute(): Unit = super.execute()
}

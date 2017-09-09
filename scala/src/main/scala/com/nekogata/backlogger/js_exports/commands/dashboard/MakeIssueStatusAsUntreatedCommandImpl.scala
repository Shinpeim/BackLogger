package com.nekogata.backlogger.js_exports.commands.dashboard

import com.nekogata.backlogger.application.command.dashboard.MakeIssueStatusAsUntreatedCommand

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("MakeIssueStatusAsUntreatedCommand")
class MakeIssueStatusAsUntreatedCommandImpl extends MakeIssueStatusAsUntreatedCommand {
  @JSExport
  override var projectId: js.UndefOr[Int] = js.undefined

  @JSExport
  override var issueId: js.UndefOr[Int] = js.undefined

  @JSExport
  override def execute(): Unit = super.execute()
}

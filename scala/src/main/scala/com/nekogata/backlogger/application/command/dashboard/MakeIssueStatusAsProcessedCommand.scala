package com.nekogata.backlogger.application.command.dashboard

import com.nekogata.backlogger.application.service.dashboard.ChangeStatusService

import scala.scalajs.js

trait MakeIssueStatusAsProcessedCommand {
  var projectId: js.UndefOr[Int]
  var issueId: js.UndefOr[Int]

  def projectIdAsOption = projectId.toOption
  def issueIdAsOption = issueId.toOption

  def execute(): Unit = {
    for {
      pid <- projectIdAsOption
      iid <- issueIdAsOption
    } {
      val service = new ChangeStatusService
      service.makeStatusAsProcessed(pid, iid)
    }
  }
}

package com.nekogata.backlogger.application.command.dashboard

import scala.scalajs.js

trait MakeIssueStatusAsProcessingCommand {
  var projectId: js.UndefOr[Int]
  var issueId: js.UndefOr[Int]

  def projectIdAsOption = projectId.toOption
  def issueIdAsOption = issueId.toOption

  def execute(): Unit = {
    println(projectId)
    println(issueId)
  }
}

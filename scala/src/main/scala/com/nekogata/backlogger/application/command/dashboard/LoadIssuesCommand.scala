package com.nekogata.backlogger.application.command.dashboard

import com.nekogata.backlogger.application.service.dashboard.LoadIssuesService

import scala.scalajs.js

trait LoadIssuesCommand {
  var projectId: js.UndefOr[Int]

  def projectIdAsOption: Option[Int] = projectId.toOption

  def execute(): Unit = {
    val service = new LoadIssuesService
    service.execute(this)
  }
}

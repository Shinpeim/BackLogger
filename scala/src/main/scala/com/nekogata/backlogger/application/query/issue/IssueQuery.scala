package com.nekogata.backlogger.application.query.issue

import com.nekogata.backlogger.domain.issue._

import scala.scalajs.js

trait IssueQuery {
  val issueRepository: IssueRepository

  import js.JSConverters._

  def allOf(projectId: Int): js.Array[js.Any] = {
    issueRepository.getAllOf(projectId).map(issue => {
      js.Dynamic.literal(
        "id" -> issue.id,
        "summary" -> issue.summary,
        "status" -> (issue.status match {
          case Untreated => "untreated"
          case Processing => "processing"
          case Processed => "processed"
        }),
        "synchronizing" -> issue.synchronizing
      ): js.Any
    }).toJSArray
  }
}

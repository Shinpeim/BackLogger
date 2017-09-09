package com.nekogata.backlogger.js_exports.queries.issue

import com.nekogata.backlogger.application.query.issue.IssueQuery
import com.nekogata.backlogger.domain.issue.{Issue, IssueRepository}
import com.nekogata.backlogger.infrastructure.issue.IssueRepositoryImpl

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("IssueQuery")
class IssueQueryImpl extends IssueQuery {
  override val issueRepository: IssueRepository = new IssueRepositoryImpl

  @JSExport
  override def allOf(projectId: Int): js.Array[js.Any] = super.allOf(projectId)
}

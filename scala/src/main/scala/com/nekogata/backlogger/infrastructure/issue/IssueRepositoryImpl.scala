package com.nekogata.backlogger.infrastructure.issue

import com.nekogata.backlogger.domain.issue.{Issue, IssueRepository}

class IssueRepositoryImpl extends IssueRepository {
  override def get(projectId: Int, id: Int): Issue = ???

  override def getAllOf(projectId: Int): Seq[Issue] =
    IssueRepositoryImpl.status.getOrElse(projectId, Seq.empty)

  override def replaceAllOf(projectId: Int, is: Seq[Issue]): Unit = {
    IssueRepositoryImpl.status = IssueRepositoryImpl.status.updated(projectId, is)
  }

  override def store(projectId: Int, i: Issue): Unit = ???
}

object IssueRepositoryImpl {
  private var status: Map[Int, Seq[Issue]] = Map.empty
}

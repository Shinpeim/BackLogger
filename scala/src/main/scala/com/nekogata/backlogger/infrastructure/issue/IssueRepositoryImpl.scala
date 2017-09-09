package com.nekogata.backlogger.infrastructure.issue

import com.nekogata.backlogger.domain.issue.{Issue, IssueRepository}

class IssueRepositoryImpl extends IssueRepository {
  override def get(id: Int): Issue = ???

  override def getAll(): Seq[Issue] = ???

  override def replaceAll(is: Seq[Issue]): Unit = {
    IssueRepositoryImpl.status = is
  }

  override def store(i: Issue): Unit = ???
}

object IssueRepositoryImpl {
  private var status: Seq[Issue] = Seq.empty
}

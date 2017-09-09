package com.nekogata.backlogger.infrastructure.issue

import com.nekogata.backlogger.domain.issue.{Issue, IssueRepository}

class IssueRepositoryImpl extends IssueRepository {
  override def get(projectId: Int, id: Int): Issue = {
    //かならずあるはずなのでない場合は普通に例外あげちゃう
    val issues = IssueRepositoryImpl.status(projectId)
    issues.find(i => i.id == id).get
  }

  override def getAllOf(projectId: Int): Seq[Issue] =
    IssueRepositoryImpl.status.getOrElse(projectId, Seq.empty)

  override def replaceAllOf(projectId: Int, is: Seq[Issue]): Unit = {
    IssueRepositoryImpl.status = IssueRepositoryImpl.status.updated(projectId, is)
  }

  override def store(projectId: Int, i: Issue): Unit = {
    val issues = IssueRepositoryImpl.status(projectId)
    val index = issues.indexWhere(iInDB => iInDB.id == i.id)
    IssueRepositoryImpl.status =
      IssueRepositoryImpl.status.updated(projectId, issues.updated(index, i))
  }

  override def remove(projectId: Int, issueId: Int): Unit = {
    val issues = IssueRepositoryImpl.status(projectId)
    IssueRepositoryImpl.status =
      IssueRepositoryImpl.status.updated(projectId, issues.filterNot(i => i.id == issueId))
  }
}

object IssueRepositoryImpl {
  private var status: Map[Int, Seq[Issue]] = Map.empty
}

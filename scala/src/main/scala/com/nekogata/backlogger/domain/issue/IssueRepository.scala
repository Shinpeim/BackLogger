package com.nekogata.backlogger.domain.issue

trait IssueRepository {
  def replaceAllOf(projectId: Int, is: Seq[Issue]): Unit

  def getAllOf(projectId: Int): Seq[Issue]

  def get(projectId:Int, IssueId: Int): Issue

  def store(projectId: Int, issue: Issue): Unit
}

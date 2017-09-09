package com.nekogata.backlogger.domain.issue

trait IssueRepository {
  def replaceAll(is: Seq[Issue]): Unit

  def getAll(): Seq[Issue]

  def get(id: Int): Issue

  def store(i: Issue): Unit
}

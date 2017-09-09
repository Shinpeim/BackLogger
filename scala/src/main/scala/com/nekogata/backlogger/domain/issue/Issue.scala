package com.nekogata.backlogger.domain.issue

case class Issue(id: Int, summary: String, status: IssueStatus, synchronizing: Boolean = false) {

  def makeStatusUntreated(): Issue = copy(status = Untreated, synchronizing = true)

  def makeStatusProcessing(): Issue = copy(status = Processing, synchronizing = true)

  def makeStatusProcessed(): Issue = copy(status = Processed, synchronizing = true)

  def commitSynchronizing = copy(synchronizing = false)
}

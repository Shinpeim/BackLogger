package com.nekogata.backlogger.domain.issue

case class Issue(id: Int, key: String, summary: String, status: IssueStatus, synchronizing: Boolean = false) {

  def makeStatusUntreatedAndSync(): Issue = copy(status = Untreated, synchronizing = true)

  def makeStatusProcessingAndSync(): Issue = copy(status = Processing, synchronizing = true)

  def makeStatusProcessedAndSync(): Issue = copy(status = Processed, synchronizing = true)

  def startSynchronizing = copy(synchronizing = true)

  def commitSynchronizing = copy(synchronizing = false)
}

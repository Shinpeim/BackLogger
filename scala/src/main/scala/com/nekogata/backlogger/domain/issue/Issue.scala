package com.nekogata.backlogger.domain.issue

case class Issue(id: Int, summary: String, status: IssueStatus) {

  def makeStatusUntreated(): Issue = copy(status = Untreated)

  def makeStatusProcessing(): Issue = copy(status = Processing)

  def makeStatusProcessed(): Issue = copy(status = Processed)

}

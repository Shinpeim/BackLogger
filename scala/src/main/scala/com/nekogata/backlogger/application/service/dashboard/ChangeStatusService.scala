package com.nekogata.backlogger.application.service.dashboard

import com.nekogata.backlogger.domain.issue.IssueRepository
import com.nekogata.backlogger.infrastructure.issue.IssueRepositoryImpl

class ChangeStatusService {
  val issueRepository: IssueRepository = new IssueRepositoryImpl

  def makeStatusAsProcessing(projectId: Int, issueId: Int): Unit = {
    val oldIssue = issueRepository.get(projectId, issueId)
    val issue = oldIssue.makeStatusProcessing()
    issueRepository.store(projectId, issue)
  }
}

package com.nekogata.backlogger.infrastructure.setting

import com.nekogata.backlogger.domain.setting.ApiKeyVerifier

import scala.concurrent.Future

class ApiKeyVerifierImpl extends ApiKeyVerifier {
  override def verify(apiKey: String): Future[Boolean] = ???
}

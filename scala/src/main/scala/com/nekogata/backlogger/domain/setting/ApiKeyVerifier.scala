package com.nekogata.backlogger.domain.setting

import scala.concurrent.Future

trait ApiKeyVerifier {
  def verify(apiKey: String): Future[Boolean]
}

package com.nekogata.backlogger.domain.setting

import scala.concurrent.Future

trait ApiKeyVerifier {
  def verify(spaceName: String, apiKey: String): Future[Boolean]
}

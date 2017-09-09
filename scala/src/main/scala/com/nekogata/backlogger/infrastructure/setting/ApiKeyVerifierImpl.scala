package com.nekogata.backlogger.infrastructure.setting

import com.nekogata.backlogger.domain.setting.ApiKeyVerifier
import com.nekogata.backlogger.infrastructure.api_client.BackLogApiClient

import scala.concurrent.Future

class ApiKeyVerifierImpl extends ApiKeyVerifier {
  override def verify(spaceName: String, apiKey: String): Future[Boolean] = {
    import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

    val client = new BackLogApiClient(spaceName, apiKey)
    // リクエストが成功すればAPIキーは正常なので中身見ないでOK
    client.getMyself.map(_ => true)
  }
}

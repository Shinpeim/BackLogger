package com.nekogata.backlogger.infrastructure.setting

import com.nekogata.backlogger.domain.setting.ApiKeyVerifier
import org.scalajs.dom.ext.Ajax

import scala.concurrent.Future

class ApiKeyVerifierImpl extends ApiKeyVerifier {
  override def verify(spaceName: String, apiKey: String): Future[Boolean] = {
    import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

    val data = Ajax.InputData.str2ajax("")
    val response = Ajax(
      method = "GET",
      url = s"https://${spaceName}.backlog.jp/api/v2/users/myself?apiKey=${apiKey}",
      data = data,
      timeout = 5000,
      headers = Map.empty,
      withCredentials = false,
      responseType = "json"
    )

    response.map(_ => {
      // リクエストが成功してれば認証OK
      true
    })
  }
}

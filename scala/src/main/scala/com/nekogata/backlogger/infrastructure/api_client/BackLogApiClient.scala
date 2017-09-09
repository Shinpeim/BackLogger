package com.nekogata.backlogger.infrastructure.api_client

import org.scalajs.dom.ext.Ajax

import scala.concurrent.Future
import scala.scalajs.js

class BackLogApiClient(spaceName: String, apiKey: String) {

  def getMyself: Future[js.Dynamic] = {
    import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

    Ajax(
      method = "GET",
      url = s"https://${spaceName}.backlog.jp/api/v2/users/myself?apiKey=${apiKey}",
      data = Ajax.InputData.str2ajax(""),
      timeout = 5000,
      headers = Map.empty,
      withCredentials = false,
      responseType = "json"
    ).map(req => req.response.asInstanceOf[js.Dynamic])
  }
}

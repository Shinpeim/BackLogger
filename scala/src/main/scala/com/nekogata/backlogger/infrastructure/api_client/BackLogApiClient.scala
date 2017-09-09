package com.nekogata.backlogger.infrastructure.api_client

import com.nekogata.backlogger.domain.issue.{Processed, Processing, Untreated}
import org.scalajs.dom.ext.Ajax

import scala.collection.mutable
import scala.concurrent.Future
import scala.scalajs.js

class BackLogApiClient(spaceName: String, apiKey: String) {
  import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

  def getMyself: Future[js.Dynamic] = {
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

  def getProjects: Future[Seq[js.Dynamic]] = {
     Ajax(
      method = "GET",
      url = s"https://${spaceName}.backlog.jp/api/v2/projects?apiKey=${apiKey}",
      data = Ajax.InputData.str2ajax(""),
      timeout = 5000,
      headers = Map.empty,
      withCredentials = false,
      responseType = "json"
    ).map(req => Seq(req.response.asInstanceOf[js.Array[js.Dynamic]]: _*))
  }

  def getIssuesOf(projectId: Int, userId: Int): Future[Seq[js.Dynamic]] = {
    Ajax(
      method = "GET",
      url = s"https://${spaceName}.backlog.jp/api/v2/issues?apiKey=${apiKey}" +
        s"&projectId[]=${projectId}&assigneeId[]=${userId}" +
        s"&statusId[]=${Untreated.id}&statusId[]=${Processing.id}&statusId[]=${Processed.id}",
      data = Ajax.InputData.str2ajax(""),
      timeout = 5000,
      headers = Map.empty,
      withCredentials = false,
      responseType = "json"
    ).map(req => Seq(req.response.asInstanceOf[js.Array[js.Dynamic]]: _*))
  }
}

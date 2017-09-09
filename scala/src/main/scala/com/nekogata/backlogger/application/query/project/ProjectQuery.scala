package com.nekogata.backlogger.application.query.project

import com.nekogata.backlogger.domain.project.{Project, ProjectRepository}

import scala.scalajs.js

trait ProjectQuery {
  val projectRepository: ProjectRepository

  import js.JSConverters._
  def all(): js.Array[js.Any] = projectRepository.getAll().map((p) => {
    js.Dynamic.literal("id" -> p.id, "name" -> p.name, "key" -> p.projectKey): js.Any
  }).toJSArray

}

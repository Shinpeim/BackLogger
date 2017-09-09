package com.nekogata.backlogger.js_exports.queries.project

import com.nekogata.backlogger.application.query.project.ProjectQuery
import com.nekogata.backlogger.domain.project.{Project, ProjectRepository}
import com.nekogata.backlogger.infrastructure.project.ProjectRepositoryImpl

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("ProjectQuery")
class ProjectQueryImpl extends ProjectQuery {
  override val projectRepository: ProjectRepository = new ProjectRepositoryImpl

  @JSExport
  override def all(): js.Array[js.Any] = super.all()
}

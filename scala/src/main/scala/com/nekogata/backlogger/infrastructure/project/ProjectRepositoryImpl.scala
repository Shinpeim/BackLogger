package com.nekogata.backlogger.infrastructure.project

import com.nekogata.backlogger.domain.project.{Project, ProjectRepository}

class ProjectRepositoryImpl extends ProjectRepository {
  override def replaceAll(ps: Seq[Project]): Unit = ProjectRepositoryImpl.state = ps

  override def getAll(): Seq[Project] = ProjectRepositoryImpl.state
}

object ProjectRepositoryImpl {
  private var state: Seq[Project] = Seq.empty
}

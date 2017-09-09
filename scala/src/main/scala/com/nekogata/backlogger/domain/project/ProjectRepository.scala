package com.nekogata.backlogger.domain.project

trait ProjectRepository {
  def replaceAll(ps: Seq[Project]): Unit
  def getAll(): Seq[Project]
}

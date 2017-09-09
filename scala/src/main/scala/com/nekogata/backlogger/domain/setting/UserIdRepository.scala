package com.nekogata.backlogger.domain.setting

trait UserIdRepository {
  def get(): String
  def store(id: String): Unit
}

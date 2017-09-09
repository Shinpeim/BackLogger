package com.nekogata.backlogger.domain.setting

trait UserIdRepository {
  def get(): Int
  def store(id: Int): Unit
}

package com.nekogata.backlogger.infrastructure.setting

import com.nekogata.backlogger.domain.setting.UserIdRepository

class UserIdRepositoryImpl extends UserIdRepository {
  override def get(): String = UserIdRepositoryImpl.state
  override def store(id: String): Unit = UserIdRepositoryImpl.state = id
}

object UserIdRepositoryImpl {
  private var state: String = ""
}

package com.nekogata.backlogger.infrastructure.setting

import com.nekogata.backlogger.domain.setting.UserIdRepository

class UserIdRepositoryImpl extends UserIdRepository {
  override def get(): Int= UserIdRepositoryImpl.state
  override def store(id: Int): Unit = UserIdRepositoryImpl.state = id
}

object UserIdRepositoryImpl {
  private var state: Int = 0
}

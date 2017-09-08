package com.nekogata.backlogger.infrastructure.setting

import com.nekogata.backlogger.domain.setting.{Setting, SettingRepository}

class SettingRepositoryImpl extends SettingRepository{
  override def get(): Setting = SettingRepositoryImpl.state
  override def store(s: Setting): Unit = {
    SettingRepositoryImpl.state = s
    println(s)
  }
}

//ひとまずオンメモリに持つ
object SettingRepositoryImpl {
  private var state: Setting = Setting("")
}


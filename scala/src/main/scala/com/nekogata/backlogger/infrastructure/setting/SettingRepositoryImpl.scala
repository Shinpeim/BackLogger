package com.nekogata.backlogger.infrastructure.setting

import com.nekogata.backlogger.domain.setting.{Setting, SettingRepository}
import org.scalajs.dom.ext.LocalStorage

class SettingRepositoryImpl extends SettingRepository{
  override def get(): Setting = {
    val setting = for {
      spaceName <- LocalStorage("Setting.spaceName")
      apiKey <- LocalStorage("Setting.apiKey")
    } yield Setting(apiKey, spaceName)

    setting.getOrElse(Setting("", ""))
  }

  override def store(s: Setting): Unit = {
    LocalStorage.update("Setting.spaceName", s.spaceName)
    LocalStorage.update("Setting.apiKey", s.apiKey)
  }
}


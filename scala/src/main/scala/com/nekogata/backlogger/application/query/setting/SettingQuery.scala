package com.nekogata.backlogger.application.query.setting

import com.nekogata.backlogger.domain.setting.SettingRepository

trait SettingQuery {
  val settingRepository: SettingRepository

  def apiKey(): String = settingRepository.get().apiKey
}

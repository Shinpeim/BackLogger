package com.nekogata.backlogger.application.service.setting

import com.nekogata.backlogger.application.command.setting.SaveSettingCommand
import com.nekogata.backlogger.domain.setting.SettingRepository
import com.nekogata.backlogger.infrastructure.setting.SettingRepositoryImpl
import com.nekogata.backlogger.js_exports.events.SettingEvents

class SaveSettingService {
  val settingRepository: SettingRepository = new SettingRepositoryImpl

  def execute(command: SaveSettingCommand): Unit = {
    val setting = settingRepository.get()
    val newSetting = setting.setApiKey(command.apiKey)
    settingRepository.store(newSetting)

    SettingEvents.saved.fire()
  }
}

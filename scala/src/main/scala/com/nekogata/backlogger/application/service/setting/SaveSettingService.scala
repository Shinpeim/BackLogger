package com.nekogata.backlogger.application.service.setting

import com.nekogata.backlogger.application.command.setting.SaveSettingCommand
import com.nekogata.backlogger.domain.setting.{ApiKeyVerifier, SettingRepository}
import com.nekogata.backlogger.infrastructure.setting.{ApiKeyVerifierImpl, SettingRepositoryImpl}
import com.nekogata.backlogger.js_exports.events.SettingEvents

class SaveSettingService {
  val settingRepository: SettingRepository = new SettingRepositoryImpl
  val apiKeyVerifier: ApiKeyVerifier = new ApiKeyVerifierImpl

  def execute(command: SaveSettingCommand): Unit = {
    import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

    val result = apiKeyVerifier.verify(command.apiKey)

    result.foreach(isOK => {
      if (isOK) {
        val setting = settingRepository.get()
        val newSetting = setting.setApiKey(command.apiKey)
        settingRepository.store(newSetting)

        SettingEvents.saved.fire()
      } else {
        SettingEvents.apiKeyVerificationFailed.fire()
      }
    })


  }
}

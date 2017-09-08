package com.nekogata.backlogger.application.service.setting

import com.nekogata.backlogger.application.command.setting.SaveSettingCommand
import com.nekogata.backlogger.domain.setting.{ApiKeyVerifier, SettingRepository}
import com.nekogata.backlogger.infrastructure.setting.{ApiKeyVerifierImpl, SettingRepositoryImpl}
import com.nekogata.backlogger.js_exports.events.SettingEvents

import scala.util.{Failure, Success}

class SaveSettingService {
  val settingRepository: SettingRepository = new SettingRepositoryImpl
  val apiKeyVerifier: ApiKeyVerifier = new ApiKeyVerifierImpl

  def execute(command: SaveSettingCommand): Unit = {

    import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
    val result = apiKeyVerifier.verify(command.spaceName, command.apiKey)

    result.onComplete {
      case Success(isOK) => {
        if (isOK) {
          save(command)
        } else {
          SettingEvents.apiKeyVerificationFailed.fire()
        }
      }
      case Failure(_) => {
        SettingEvents.apiKeyVerificationFailed.fire()
      }
    }
  }

  private def save(command: SaveSettingCommand) = {
    val setting = settingRepository.get()
    val newSetting = setting.setApiKey(command.apiKey).setSpaceName(command.spaceName)
    println(newSetting)
    settingRepository.store(newSetting)
    SettingEvents.saved.fire()
  }
}

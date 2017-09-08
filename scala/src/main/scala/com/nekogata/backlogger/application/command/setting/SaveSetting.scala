package com.nekogata.backlogger.application.command.setting

trait SaveSetting {
  def save(apiKey: String) = {
    println(apiKey)
  }
}

package com.nekogata.backlogger.domain.setting

case class Setting(val apiKey:String, val spaceName: String) {
  def setApiKey(k: String) = copy(apiKey = k)
  def setSpaceName(s: String) = copy(spaceName = s)
}

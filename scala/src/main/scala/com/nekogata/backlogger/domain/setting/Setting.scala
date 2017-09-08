package com.nekogata.backlogger.domain.setting

case class Setting(val apiKey:String) {
  def setApiKey(k: String) = copy(apiKey = k)
}

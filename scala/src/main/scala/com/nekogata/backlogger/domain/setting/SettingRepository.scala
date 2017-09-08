package com.nekogata.backlogger.domain.setting

trait SettingRepository {
  def store(s: Setting): Unit
  def get(): Setting
}

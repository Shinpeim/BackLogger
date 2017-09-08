package com.nekogata.backlogger.js_exports.queries.setting

import com.nekogata.backlogger.application.query.setting.SettingQuery
import com.nekogata.backlogger.domain.setting.SettingRepository
import com.nekogata.backlogger.infrastructure.setting.SettingRepositoryImpl

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("SettingQuery")
class SettingQueryImpl extends SettingQuery {
  override val settingRepository: SettingRepository =  new SettingRepositoryImpl

  @JSExport
  override def apiKey(): String = super.apiKey()
}

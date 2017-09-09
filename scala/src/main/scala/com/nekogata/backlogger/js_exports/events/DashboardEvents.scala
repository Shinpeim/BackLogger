package com.nekogata.backlogger.js_exports.events

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("DashboardEvents")
object DashboardEvents {
  @JSExport val initialized = new Event {
    @JSExport override def subscribe(f: js.Function): Subscription = super.subscribe(f)
  }
}

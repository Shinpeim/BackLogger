package com.nekogata.backlogger.js_exports.events
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("NetworkEvents")
object NetworkEvents {
  @JSExport
  val requestFailed = new Event {
    @JSExport
    override def subscribe(f: js.Function): Subscription = super.subscribe(f)
  }
}

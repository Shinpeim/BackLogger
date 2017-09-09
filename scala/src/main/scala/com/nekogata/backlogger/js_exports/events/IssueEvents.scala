package com.nekogata.backlogger.js_exports.events

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("IssueEvents")
object IssueEvents {
  @JSExport
  val loaded = new Event {
    @JSExport
    override def subscribe(f: js.Function): Subscription = super.subscribe(f)
  }

  @JSExport
  val repositoryChanged = new Event {
    @JSExport
    override def subscribe(f: js.Function): Subscription = super.subscribe(f)
  }
}

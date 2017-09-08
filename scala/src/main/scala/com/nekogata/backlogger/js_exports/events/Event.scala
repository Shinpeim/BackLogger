package com.nekogata.backlogger.js_exports.events

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

trait Event{
  private var subscriptions: Set[Subscription] = Set.empty

  def subscribe(f: js.Function): Subscription = {
    val s = new Subscription(this, f)
    subscriptions = subscriptions + s
    s
  }

  def fire() = {
    subscriptions.foreach(_.f.call((): Unit))
  }

  def unsubscribe(s: Subscription): Unit ={
    subscriptions = subscriptions - s
  }
}

class Subscription(dispatcher: Event, val f: js.Function){
  @JSExport
  def unsubscribe() = {
    dispatcher.unsubscribe(this)
  }
}

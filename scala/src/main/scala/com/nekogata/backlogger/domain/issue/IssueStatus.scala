package com.nekogata.backlogger.domain.issue

sealed trait IssueStatus {
  val id: Int
}

// APIから取得することもできるんだけどどうやら固定っぽいので
// こちらでも固定で扱う
// このアプリでは完了済みの課題は扱わないので定義しない
case object Untreated extends IssueStatus {
  override val id = 1
}

case object Processing extends IssueStatus {
  override val id = 2
}

case object Processed extends IssueStatus {
  override val id = 3
}

// 定義しない
//case object Closed extends IssueStatus {
//  override val id = 4
//}

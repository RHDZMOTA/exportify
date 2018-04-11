package com.rhdzmota.json

object JsSyntax {

  implicit class JsWriterOps[A](value: A) {
    def toJson(implicit writer: JsWriter[A]): JsValue =
      writer write value
  }
}

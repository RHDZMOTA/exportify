package com.rhdzmota.json

object JsUtil {
  def toJson[A](value: A)(implicit writer: JsWriter[A]): JsValue =
    writer write value
}

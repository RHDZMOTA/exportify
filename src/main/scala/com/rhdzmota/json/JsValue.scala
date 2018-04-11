package com.rhdzmota.json

sealed trait JsValue {
  def stringify: String
}

final case class JsObject(values: Map[String, JsValue]) extends JsValue {
  override def stringify: String = values map {
    case (name, value) => "\"" + name + "\": " + value.stringify
  } mkString("{", ",", "}")
}

final case class JsString(value: String) extends JsValue {
  override def stringify: String = "\"" + value.replaceAll("\\|\"", "\\\\$1") + "\""
}

final case class JsList(value: List[JsValue]) extends JsValue {
  override def stringify: String = value map {_.stringify} mkString("[", ",", "]")
}

case object JsNull extends JsValue {
  override def stringify: String = "null"
}


// Define the numbers

sealed trait JsNumber[A] extends JsValue {
  def value: A
  override def stringify: String = value.toString
}

final case class JsDouble(value: Double) extends JsNumber[Double] with JsValue

final case class JsInt(value: Int) extends JsNumber[Int] with JsValue

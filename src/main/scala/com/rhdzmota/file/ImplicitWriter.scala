package com.rhdzmota.file

import com.rhdzmota.json.{JsString, JsValue}

import scala.util.Try

trait ImplicitWriter[A] {
  def writeTo(fileName: String, content: A): Try[Unit]
}

object WriterImplicits {

  implicit object ImplicitStringFileWriter extends ImplicitWriter[String] {
    override def writeTo(fileName: String, content: String): Try[Unit] = StringFileWriter(fileName, content).write
  }

  implicit object ImplicitJsonFileWriter extends ImplicitWriter[JsValue] {
    override def writeTo(fileName: String, content: JsValue): Try[Unit] = JsonFileWriter(fileName, content).write
  }
}

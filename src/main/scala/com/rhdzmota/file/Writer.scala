package com.rhdzmota.file

import java.io.{BufferedWriter, File, FileWriter}

import com.rhdzmota.json.JsValue

import scala.util.Try

sealed trait Writer[A] {
  def fileName: String
  def content: A
  def write: Try[Unit]
  def bufferedWriter: Try[BufferedWriter] = Try(new File(fileName))
    .map {new FileWriter(_)}
    .map {new BufferedWriter(_)}
}

case class StringFileWriter(fileName: String, content: String) extends Writer[String] {
  override def write: Try[Unit] = bufferedWriter map { bw =>
    bw.write(content)
    bw.close()
  }
}

case class JsonFileWriter(fileName: String, content: JsValue) extends Writer[JsValue] {
  override def write: Try[Unit] = bufferedWriter map { bw =>
    bw.write(content.stringify)
    bw.close()
  }
}


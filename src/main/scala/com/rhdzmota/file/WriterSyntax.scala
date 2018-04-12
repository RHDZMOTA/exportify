package com.rhdzmota.file

import scala.util.Try

object WriterSyntax {

  implicit class WriterOps[A](value: A) {
    def writeTo(fileName: String)(implicit implicitWriter: ImplicitWriter[A]): Try[Unit] =
      implicitWriter.writeTo(fileName, value)
  }

}

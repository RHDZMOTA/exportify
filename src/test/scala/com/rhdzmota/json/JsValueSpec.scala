package com.rhdzmota.json

import org.scalatest._

class JsValueSpec extends FlatSpec with Matchers {

  "JsNull object" should "stringify to null" in {
    val jsNull = JsNull
    jsNull.stringify shouldBe "null"
  }

  "JsString class" should "stringify instance JsString(Hey) to \"Hey\"" in {
    val jsString = JsString("Hey")
    jsString.stringify shouldBe "\"Hey\""
  }

  "JsDouble class" should "stringify instance JsDouble(42) to \"42.0\"" in {
    val jsDouble = JsDouble(42)
    jsDouble.stringify shouldBe "42.0"
  }

  "JsInt class" should "stringify instance JsInt(42) to \"42\"" in {
    val jsInt = JsInt(42)
    jsInt.stringify shouldBe "42"
  }

  "Pattern matching" should "work with JsNumber trait" in {
    def stringifyThis[A](value: JsNumber[A]): String = value match {
      case v: JsDouble => s"Double: ${v.stringify}"
      case v: JsInt => s"Int: ${v.stringify}"
    }

    stringifyThis(JsInt(42)) shouldBe "Int: 42"
    stringifyThis(JsDouble(42)) shouldBe "Double: 42.0"
  }

  "JsList class" should "stringify instance JsList(List(JsInt(1), JsInt(2), JsInt(3))) to [1,2,3]" in {
    val jsList = JsList(List(JsInt(1), JsInt(2), JsInt(3)))
    jsList.stringify shouldBe "[1,2,3]"
  }

  "JsObject class" should "stringify instance JsObject(Map(Hello -> JsString(World))) to {\"Hello\": \"World\"}" in {
    val jsObject = JsObject(Map(
      "Hello" -> JsString("World")
    ))
    jsObject.stringify shouldBe "{\"Hello\": \"World\"}"
  }

}

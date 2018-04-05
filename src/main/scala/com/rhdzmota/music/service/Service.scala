package com.rhdzmota.music.service

import com.rhdzmota.music.driver.SeleniumDriver
import com.rhdzmota.music.models._

trait Service {

  def account: Account
  def driver: SeleniumDriver

  // Initialize session
  def initialize(): Unit

}

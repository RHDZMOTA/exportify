package com.rhdzmota.music.service

import com.rhdzmota.music.driver.SeleniumDriver
import com.rhdzmota.music.models._

trait Service {

  def account: Account
  def driver: SeleniumDriver

  def openHome(): Unit = this match {
    case googleService: GoogleMusic  => googleService.driver.browser.goTo("https://play.google.com/music/listen")
    case spotifyService: Spotify => spotifyService.driver.browser.goTo("https://open.spotify.com/browse")
    case _ => println("Invalid. ")
  }

  /**
  def logIn(): Unit = this match {
    case googleService: Service[GoogleMusic] => ???
    case spotifyService: Service[Spotify] => ???
  }
    **/

}

package com.rhdzmota.music.service

import com.rhdzmota.music.driver.Selenium
import com.rhdzmota.music.models._

trait Service {

  def account: Account
  def selenium: Selenium

  def openHome(): Unit = this match {
    case googleService: GoogleMusic  => googleService.selenium.browser.get("https://play.google.com/music/listen")
    case spotifyService: Spotify => spotifyService.selenium.browser.get("https://open.spotify.com/browse")
    case _ => println("Invalid. ")
  }

  def getLang: Lang = this match {
    case googleService: GoogleMusic => googleService.selenium.browser getTitle() match {
      case es if es contains "Música" => Es
      case en if en contains "Music" => En
      case _ => Unknown
    }
    case spotifyService: Spotify => spotifyService.selenium.browser getTitle() match {
      case en if en contains "Web Player" => En
      case _ => Unknown
    }
  }

  def clickSignInButton(): Unit = this match {
    case googleService: GoogleMusic => googleService.getLang match {
      case Es => googleService.selenium.browser findElementByLinkText "Iniciar sesión" click()
      case En => googleService.selenium.browser findElementByLinkText "Sign in" click() // TODO: verify text
      case Unknown => println("Undefined") // TODO: raise error
    }
    case spotifyService: Spotify => spotifyService.getLang match {
      case En => spotifyService.selenium.browser findElementById "has-account" click()
      case Unknown => println("Undefined") // TODO: raise error
    }
  }

  def signIn(user: User): Unit = this match {
    case googleService: GoogleMusic =>
      googleService.selenium.browser findElementByXPath "//input[@type=\"email\"]" sendKeys user.email
      googleService.selenium.browser findElementById "identifierNext" click()
      // TODO: continue
    case spotifyService: Spotify =>
      spotifyService.selenium.browser findElementById "login-username" sendKeys user.email
      spotifyService.selenium.browser findElementById "login-password" sendKeys user.pwd
      spotifyService.selenium.browser findElementById "g-recaptcha-button" click()
  }

}

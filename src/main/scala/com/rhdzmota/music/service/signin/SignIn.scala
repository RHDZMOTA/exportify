package com.rhdzmota.music.service.signin

import com.rhdzmota.music.driver.Selenium
import com.rhdzmota.music.models._
import com.rhdzmota.music.models.user.{Account, User}
import com.rhdzmota.music.service.download.Downloader
import com.rhdzmota.music.service.download.impl.{GoogleMusicDownloader, SpotifyDownloader}

trait SignIn {

  def account: Account
  def selenium: Selenium

  def openHome(): Unit = this match {
    case google: GoogleMusic  => google.selenium.browser.get("https://play.google.com/music/listen")
    case spotify: Spotify => spotify.selenium.browser.get("https://open.spotify.com/browse")
    case _ => println("Invalid. ")
  }

  def getLang: Lang = this match {
    case google: GoogleMusic => google.selenium.browser getTitle() match {
      case en if en contains "Music" => En
      case es if es contains "Música" => Es
      case _ => Unknown
    }
    case spotify: Spotify => spotify.selenium.browser getTitle() match {
      case en if en contains "Web Player" => En
      case es if es contains "Web Player" => Es
      case _ => Unknown
    }
  }

  def clickSignInButton(): Unit = this match {
    case google: GoogleMusic => google.getLang match {
      case Es => google.selenium.browser findElementByLinkText "Iniciar sesión" click()
      case En => google.selenium.browser findElementByLinkText "Sign in" click() // TODO: verify text
      case Unknown => println("Undefined") // TODO: raise error
    }
    case spotify: Spotify => spotify.getLang match {
      case En => spotify.selenium.browser findElementById "has-account" click()
      case Unknown => println("Undefined") // TODO: raise error
    }
  }

  def signIn(user: User): Unit = this match {
    case google: GoogleMusic =>
      google.selenium.browser findElementByXPath "//input[@type=\"email\"]" sendKeys user.email
      google.selenium.browser findElementById "identifierNext" click()
      Thread.sleep(5000)
      google.selenium.browser findElementByXPath "//input[@type=\"password\"]" sendKeys user.pwd
      google.selenium.browser findElementById "passwordNext" click()
    case spotify: Spotify =>
      spotify.selenium.browser findElementById "login-username" sendKeys user.email
      spotify.selenium.browser findElementById "login-password" sendKeys user.pwd
      spotify.selenium.browser findElementById "login-button" click()
  }

  def run(): Unit = {
    openHome()
    clickSignInButton()
    Thread.sleep(2000)
    signIn(account.user)
  }

  def getDownloader: Downloader = this match {
    case google: GoogleMusic => GoogleMusicDownloader(account, selenium)
    case spotify: Spotify => SpotifyDownloader(account, selenium)
  }

}

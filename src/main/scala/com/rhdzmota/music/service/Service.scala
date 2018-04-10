package com.rhdzmota.music.service

import com.rhdzmota.music.driver.Selenium
import com.rhdzmota.music.models._
import com.rhdzmota.music.models.user.Account

trait Service {

  def account: Account
  def selenium: Selenium

  def newSelenium(): Selenium = Selenium()

  def openHome(): Unit = this match {
    case google: GoogleMusic  => google.selenium.browser.get("https://play.google.com/music/listen")
    case spotify: Spotify => spotify.selenium.browser.get("https://open.spotify.com/browse")
    case _ => println("Invalid. ")
  }

  def userHome(): Unit = this match {
    case google: GoogleMusic => Unit
    case spotify: Spotify => spotify.selenium.browser findElementByLinkText "Your Music" click()
  }

  def hasPlaylists: Boolean = this match {
    case google: GoogleMusic => false
    case spotify: Spotify =>
      (spotify.selenium.browser findElementsByClassName "collection-empty-message" size()) == 1
  }

  def playlistNames: List[String] = this match {
    case google: GoogleMusic => List("")
    case spotify: Spotify =>
      def getNames: List[String] = {
        val containerTextContent: String = selenium.browser.findElementByClassName("container-fluid").getText
        val containerArrayContent: Array[String] = containerTextContent.split("\n").filter(_ != "Playlist Icon")
        val tuple: (Array[String], String) = containerArrayContent.foldRight((Array.empty[String], "")) {
        (elm: String, acc: (Array[String], String)) => if (acc._1 contains elm) (acc._1, elm) else (acc._1 :+ elm, acc._2)
        }
      tuple._1.filter(_ != tuple._2).toList
      }
      userHome()
      Thread.sleep(3000)
      if (hasPlaylists) List.empty[String] else getNames
  }

}


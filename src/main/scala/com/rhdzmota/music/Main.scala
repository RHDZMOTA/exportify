package com.rhdzmota.music

import com.rhdzmota.music.driver.Selenium
import com.rhdzmota.music.models.{GoogleMusic, Incomplete, UserGoogleMusic, UserSpotify}
import com.rhdzmota.music.service.download.Downloader
import com.rhdzmota.music.service.download.impl.{GoogleMusicDownloader, SpotifyDownloader}
import org.openqa.selenium.By

object Main {

  def main(args: Array[String]): Unit = {

    val action = args(0)
    val service = args(1)
    val email = args(2)
    val pwd = args(3)

    if (action == "-download") initializeDownload(service, email, pwd)
    else println("Action not implemented.")

  }

  def validAction(action: String): Boolean = action match {
    case "-download" => true
    case "-upload" => true
    case _ => false
  }

  def validService(service: String): Boolean = service match {
    case "-gmusic" => true
    case "-spotify" => true
    case _ => false
  }

  def initializeDownload(service: String, email: String, pwd: String): Unit = {
    val user = service match {
      case "-gmusic" => UserGoogleMusic(email, pwd)
      case "-spotify" => UserSpotify(email, pwd)
    }
    val account = Incomplete(user)
    val selenium = Selenium()
    val downloader = service match {
      case "-gmusic" => GoogleMusicDownloader(account, selenium)
      case "-spotify" => SpotifyDownloader(account, selenium)
    }
    
    downloader.openHome()
    downloader.clickSignInButton()
    Thread.sleep(5000)
    downloader.signIn(user)
  }

}

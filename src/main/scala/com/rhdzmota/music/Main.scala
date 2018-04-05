package com.rhdzmota.music

import com.rhdzmota.music.driver.SeleniumDriver
import com.rhdzmota.music.models.{Incomplete, UserGoogleMusic}
import com.rhdzmota.music.service.download.impl.GoogleMusicDownloader

object Main {

  def main(args: Array[String]): Unit = {

    val user = UserGoogleMusic("", "")
    val account = Incomplete(user)
    val driver = SeleniumDriver()
    val downloader = GoogleMusicDownloader(account, driver)

    downloader.openHome()
    //downloader.driver.browser
  }


}

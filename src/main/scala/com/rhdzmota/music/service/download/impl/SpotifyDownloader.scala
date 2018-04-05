package com.rhdzmota.music.service.download.impl

import com.rhdzmota.music.driver.SeleniumDriver
import com.rhdzmota.music.service.download.Downloader
import com.rhdzmota.music.models.{Account, Spotify}

case class SpotifyDownloader(account: Account, driver: SeleniumDriver) extends Downloader with Spotify {

}


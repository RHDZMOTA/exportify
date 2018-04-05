package com.rhdzmota.music.service.download.impl

import com.rhdzmota.music.driver.SeleniumDriver
import com.rhdzmota.music.service.download.Downloader
import com.rhdzmota.music.models.{Account, GoogleMusic}

case class GoogleMusicDownloader(account: Account[GoogleMusic], driver: SeleniumDriver) extends Downloader[GoogleMusic] {}


package com.rhdzmota.music.service.download.impl

import com.rhdzmota.music.driver.Selenium
import com.rhdzmota.music.service.download.Downloader
import com.rhdzmota.music.models.{Account, GoogleMusic}

case class GoogleMusicDownloader(account: Account, selenium: Selenium) extends Downloader with GoogleMusic {}


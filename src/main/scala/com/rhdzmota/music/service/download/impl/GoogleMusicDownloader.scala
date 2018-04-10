package com.rhdzmota.music.service.download.impl

import com.rhdzmota.music.driver.Selenium
import com.rhdzmota.music.service.download.Downloader
import com.rhdzmota.music.models.GoogleMusic
import com.rhdzmota.music.models.user.Account

case class GoogleMusicDownloader(account: Account, selenium: Selenium) extends Downloader with GoogleMusic {}


package com.rhdzmota.music.service.download.impl

import com.rhdzmota.music.service.download.Downloader
import com.rhdzmota.music.models.GoogleMusic

case class GoogleMusicDownloader(email: String, pwd: String) extends Downloader[GoogleMusic] {}


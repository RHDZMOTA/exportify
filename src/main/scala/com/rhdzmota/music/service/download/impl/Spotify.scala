package com.rhdzmota.music.service.download.impl

import com.rhdzmota.music.service.download.Downloader
import com.rhdzmota.music.models.Spotify

case class SpotifyDownloader(email: String, pwd: String) extends Downloader[Spotify] {

}


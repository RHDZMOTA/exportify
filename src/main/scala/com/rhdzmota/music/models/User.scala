package com.rhdzmota.music.models

sealed trait User[MusicService]{
  def email: String
  def pwd: String
}

final case class GoogleMusicUser(email: String, pwd: String) extends User[GoogleMusic]
final case class SpotifyUser(email: String, pwd: String) extends User[Spotify]

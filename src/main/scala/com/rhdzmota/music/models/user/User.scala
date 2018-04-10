package com.rhdzmota.music.models.user

import com.rhdzmota.music.models.{GoogleMusic, Spotify}

sealed trait User{
  def email: String
  def pwd: String
}

final case class UserGoogleMusic(email: String, pwd: String) extends User with GoogleMusic
final case class UserSpotify(email: String, pwd: String) extends User with Spotify

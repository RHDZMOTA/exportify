package com.rhdzmota.music.models

sealed trait Account {
  def user: User
}

final case class Compete(user: User, playlists: List[Playlist]) extends Account
final case class Incomplete(user: User) extends Account

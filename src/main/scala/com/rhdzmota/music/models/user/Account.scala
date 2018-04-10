package com.rhdzmota.music.models.user

import com.rhdzmota.music.models.music.Playlist

sealed trait Account {
  def user: User
}

final case class Complete(user: User, playlists: List[Playlist]) extends Account
final case class Incomplete(user: User) extends Account

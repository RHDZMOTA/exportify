package com.rhdzmota.music.models

sealed trait Account[MusicService] {
  def user: User[MusicService]
}

final case class Compete[MusicService](user: User[MusicService], playlists: List[Playlist]) extends Account[MusicService]
final case class Incomplete[MusicService](user: User[MusicService]) extends Account[MusicService]

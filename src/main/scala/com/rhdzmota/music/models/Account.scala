package com.rhdzmota.music.models

sealed trait Account {
  def user: User
  def service: StreamingService
}

final case class Compete(user: User, service: StreamingService, playlists: List[Playlist]) extends Account
final case class Incomplete(user: User, service: StreamingService) extends Account

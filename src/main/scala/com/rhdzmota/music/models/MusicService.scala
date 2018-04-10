package com.rhdzmota.music.models

sealed trait MusicService

trait GoogleMusic extends MusicService
trait Spotify extends MusicService

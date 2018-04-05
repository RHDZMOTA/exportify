package com.rhdzmota.music.models

sealed trait MusicService

sealed trait GoogleMusic extends MusicService
sealed trait Spotify extends MusicService

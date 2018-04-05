package com.rhdzmota.music

sealed trait StreamingService {

  // Methods to initialize session
  def email: String
  def pwd: String
  def initialize(): Unit

  // Query service
  def currentPlayListsName: List[String]
  def getSongNames(playlistName: String): List[String]

  // Download data
  def downloadPlaylists: List[Playlist]

  // Add data or create
  def addSongToNewPlaylist(song: Song, playListName: String): Unit
  def addSongToPlaylist(song: Song, playListName: String): Unit

  def createPlaylist(playlist: Playlist): Unit = {
    def recursiveAdd(songs: List[Song], playListName: String): Unit = songs match {
      case Nil => println("Playlist created!")
      case headSong :: remaining =>
        addSongToPlaylist(headSong, playListName)
        recursiveAdd(remaining, playListName)
    }

    addSongToNewPlaylist(playlist.songs.head, playlist.name) // The first song is needed in order to create a playlist
    if (playlist.songs.tail.nonEmpty)
      recursiveAdd(playlist.songs.tail, playlist.name)
  }

  def createPlayLists(playLists: List[Playlist]): Unit =
    playLists foreach { this.createPlaylist }
}


case class GoogleMusic(email: String, pwd: String) extends StreamingService {}
case class Spotify    (email: String, pwd: String) extends StreamingService {}

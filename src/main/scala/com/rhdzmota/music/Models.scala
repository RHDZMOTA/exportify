package com.rhdzmota.music

case class Author(artistName: String, name: String = "", lastName: String = "")
case class Song(author: Author, name: String, album: String)
case class Playlist(name: String, songs: List[Song])

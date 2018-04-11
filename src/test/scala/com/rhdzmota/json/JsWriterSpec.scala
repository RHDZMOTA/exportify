package com.rhdzmota.json

import JsWriterInstances._
import org.scalatest._

class JsWriterSpec extends FlatSpec with Matchers{

  //import JsWriterInstances._

  def quote(s: String): String = "\"" + s + "\""

  "StringWriter implicit object" should "serialize json from \"Hello World\" string using JsUtil helper" in {
    val hello = "Hello World"
    JsUtil.toJson(hello) shouldBe JsString(hello)
  }

  "DoubleWriter and IntWriter implicit objects" should "serialize json from 42 using JsUtil helper" in {
    JsUtil.toJson(42.0) shouldBe JsDouble(42.0)
    JsUtil.toJson(42) shouldBe JsInt(42)
  }

  "ListWriter implicit object" should "serialize json form List(JsInt(1), JsInt(2), JsInt(3)) using JsUtil helper" in {
    val testList = List(JsInt(1), JsInt(2), JsInt(3))
    JsUtil.toJson(testList) shouldBe JsList(testList)
  }

  "ObjectWriter implicit object" should "serialize json form Map(\"Hello\" -> JsString(\"World\")) using JsUtil helper" in {
    val testMap = Map("Hello" -> JsString("World"))
    JsUtil.toJson(testMap) shouldBe JsObject(testMap)
  }

  // Test music models
  import com.rhdzmota.music.models.music.{Album, Author, Song, Playlist}

  "AuthorWriter implicit object" should "serialize json form Author(\"This Will Destroy You\") using JsUtil hepler" in {
    val authorName = "This Will Destroy You"
    val author = Author(authorName)
    JsUtil.toJson(author).stringify shouldBe s"{${quote("artist_name")}: ${quote(authorName)}}"
  }

  "AlbumWriter implicit object" should "serialize json form Album(...) using JsUtil helper" in {
    val albumName = "music album"
    val album = Album(albumName)
    JsUtil.toJson(album).stringify shouldBe s"{${quote("name")}: ${quote(albumName)}}"
  }

  "SongWriter implicit object" should "serialize json form Song(...) instance using JsUtil helper" in {
    val songName = "song name"
    val albumName = "album name"
    val authorName = "author name"
    val song = Song(Author(authorName), songName, Album(albumName))
    JsUtil.toJson(song).stringify shouldBe s"{${quote("author")}: {${quote("artist_name")}: ${quote(authorName)}}," +
      s"${quote("album")}: {${quote("name")}: ${quote(albumName)}},${quote("name")}: ${quote(songName)}}"
  }

  "PlaylistWriter implicit object" should "serialize json form Playlist(...) instance using JsUtil hepler" in {
    val songName = "song name"
    val albumName = "album name"
    val authorName = "author name"
    val playlistName = "playlist name"
    val song = Song(Author(authorName), songName, Album(albumName))
    val playlist = Playlist(playlistName, List(song))
    JsUtil.toJson(playlist).stringify shouldBe "{\"name\": \"playlist name\",\"songs\": [{\"author\": {\"artist_name\": \"author name\"},\"album\": {\"name\": \"album name\"},\"name\": \"song name\"}]}"
  }

  // Test user models
  import com.rhdzmota.music.models.user._
  "UserWriter implicit object" should "serialize json from Spotify and Google Music Users using JsUtil helper" in {
    val spotifyUser = UserSpotify("user@spotify.com", "spotify-password")
    val gmusicUser = UserGoogleMusic("user@googlemusic.com", "gmusic-password")
    JsUtil.toJson(spotifyUser).stringify shouldBe "{\"email\": \"user@spotify.com\",\"pwd\": \"spotify-password\"}"
    JsUtil.toJson(gmusicUser).stringify shouldBe "{\"email\": \"user@googlemusic.com\",\"pwd\": \"gmusic-password\"}"
  }

  "AccountWriter implicit object" should "serialize json from an Incomplete or Complete account using JsUtil hepler" in {
    val spotifyUser = UserSpotify("user@spotify.com", "spotify-pwd")
    val incomplete = Incomplete(spotifyUser)
    JsUtil.toJson(incomplete).stringify shouldBe "{\"user\": {\"email\": \"user@spotify.com\",\"pwd\": \"spotify-pwd\"}}"
    val songName = "song name"
    val albumName = "album name"
    val authorName = "author name"
    val playlistName = "playlist name"
    val song = Song(Author(authorName), songName, Album(albumName))
    val playlist = Playlist(playlistName, List(song))
    val complete = Complete(spotifyUser, List(playlist))
    JsUtil.toJson(complete).stringify shouldBe "{\"user\": {\"email\": \"user@spotify.com\",\"pwd\": \"spotify-pwd\"},\"playlists\": [{\"name\": \"playlist name\",\"songs\": [{\"author\": {\"artist_name\": \"author name\"},\"album\": {\"name\": \"album name\"},\"name\": \"song name\"}]}]}"
  }

}

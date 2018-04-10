package com.rhdzmota.json

import com.rhdzmota.music.models.music.{Album, Author, Playlist, Song}
import com.rhdzmota.music.models.user._

trait JsWriter[A] {
  def write(value: A): JsValue
}

object JsWriterInstances {
  import JsSyntax.JsWriterOps // Import implicit methods

  implicit object StringWriter extends JsWriter[String] {
    override def write(value: String): JsValue = value.toJson
  }


  implicit object DoubleWriter extends JsWriter[Double] {
    override def write(value: Double): JsValue = value.toJson
  }

  implicit object IntWriter extends JsWriter[Int] {
    override def write(value: Int): JsValue = value.toJson
  }

  implicit object ListWriter extends JsWriter[List[JsValue]] {
    override def write(value: List[JsValue]): JsValue = value.toJson
  }

  // Music Models
  implicit object AuthorWriter extends JsWriter[Author] {
    override def write(value: Author): JsValue = JsObject(Map(
      "artist_name" -> value.artistName.toJson
    ))
  }

  implicit object AlbumWriter extends JsWriter[Album] {
    override def write(value: Album): JsValue = JsObject(Map(
      "name" -> value.name.toJson
    ))
  }

  implicit object SongWriter extends JsWriter[Song] {
    override def write(value: Song): JsValue = JsObject(Map(
      "author" -> value.author.toJson,
      "album" -> value.album.toJson,
      "name" -> value.name.toJson
    ))

    implicit object PlaylistWriter extends JsWriter[Playlist] {
      override def write(value: Playlist): JsValue = JsObject(Map(
        "name" -> value.name.toJson,
        "songs" -> (value.songs map {_.toJson}).toJson
      ))
    }

    // User Models

    implicit object UserSpotifyWriter extends JsWriter[UserSpotify] {
      override def write(value: UserSpotify): JsValue = JsObject(Map(
        "mail" -> value.email.toJson,
        "pwd" -> value.pwd.toJson
      ))
    }

    implicit object UserWriter extends JsWriter[User] {
      override def write(value: User): JsValue = JsObject(Map(
        "mail" -> value.email.toJson
      ))
    }


    implicit object CompleteAccountWriter extends JsWriter[Complete] {
      override def write(value: Complete): JsValue = JsObject(Map(
        "user" -> value.user.toJson,
        "playlists" -> (value.playlists map {_.toJson}).toJson
      ))
    }

    implicit object AccountWriter extends JsWriter[Account] {
      override def write(value: Account): JsValue = value match {
        case v: Complete => JsObject(Map(
          "user" -> v.user.toJson,
          "playlists" -> (v.playlists map {_.toJson}).toJson
        ))
        case v: Incomplete => JsObject(Map(
          "user" -> v.user.toJson
        ))
      }
    }
  }
}

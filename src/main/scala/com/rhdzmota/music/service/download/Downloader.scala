package com.rhdzmota.music.service.download

import com.rhdzmota.music.models.{GoogleMusic, Spotify}
import com.rhdzmota.music.models.music.{Album, Author, Playlist, Song}
import com.rhdzmota.music.service.Service
import com.rhdzmota.music.service.signin.impl.SpotifySignIn
import org.openqa.selenium.By

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait Downloader extends Service {


  def getPlaylist(name: String): Option[Playlist] = this match {
    case google: GoogleMusic => None
    case spotify: Spotify =>
      if (!(playlistNames contains name)) None
      else {
        spotify.selenium.browser findElementByXPath s"//*[@title=\'$name\']" click()
        Thread.sleep(3000)
        val rawSongs = (spotify.selenium.browser findElementsByXPath "//div[@class=\'tracklist-col name\']").asScala.toList
        val songList: List[Option[Song]] = rawSongs map (x => {
          val authorAndAlbumStr: List[String] = x.findElements(By.className("link-subtle")).asScala.toList map {
            _.getText
          }
          val name: String = x.findElement(By.className("tracklist-name")).getText
          if (authorAndAlbumStr.length != 2) None
          else {
            val author: Author = Author(authorAndAlbumStr.head)
            val album: Album = Album(authorAndAlbumStr(1))
            Some(Song(author, name, album))
          }
        }
          )
        Some(Playlist(name, songList.flatten))
      }
  }

  def getFuturePlaylist(name: String): Future[Option[Playlist]] = this match {
    case google: GoogleMusic => Future(None)
    case spotify: Spotify => Future {
      val selenium = newSelenium()
      val signInService = SpotifySignIn(this.account, selenium)
      signInService.run()
      val downloader = signInService.getDownloader
      Thread.sleep(3000)
      val playlist: Option[Playlist] = downloader.getPlaylist(name)
      selenium.browser.quit()
      playlist
    }
  }

  def getPlaylistsParallel: Option[List[Future[Option[Playlist]]]] = this match {
    case google: GoogleMusic => None
    case spotify: Spotify =>
      Thread.sleep(3000)
      val result = spotify.playlistNames map {x =>
        spotify.getFuturePlaylist(x)
      }
      Some(result)
  }

  def getPlaylists: Option[List[Option[Playlist]]] = this match {
    case google: GoogleMusic => None
    case spotify: Spotify =>
      Thread.sleep(3000)
      val result = spotify.playlistNames map {x =>
        spotify.getPlaylist(x)
      }
      Some(result)
  }

}


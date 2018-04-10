package com.rhdzmota.music

import com.rhdzmota.music.driver.Selenium
import com.rhdzmota.music.models.music.{Album, Author, Playlist, Song}
import com.rhdzmota.music.models.user.{Complete, Incomplete, UserGoogleMusic, UserSpotify}
import com.rhdzmota.music.service.download.Downloader
import com.rhdzmota.music.service.signin.impl.{GoogleMusicSignIn, SpotifySignIn}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

import com.rhdzmota.json.JsSyntax.JsWriterOps
import com.rhdzmota.json.JsUtil
import com.rhdzmota.json.JsWriterInstances._

object Main {

  def main(args: Array[String]): Unit = {

    val action = args(0)
    val service = args(1)
    val email = args(2)
    val pwd = args(3)
    val parallel: Boolean = args mkString " " contains "-parallel"

    if (action == "-download") initializeDownload(service, email, pwd, parallel)
    else println("Action not implemented.")

  }

  def validAction(action: String): Boolean = action match {
    case "-download" => true
    case "-upload" => true
    case _ => false
  }

  def validService(service: String): Boolean = service match {
    case "-gmusic" => true
    case "-spotify" => true
    case _ => false
  }

  def initializeDownload(service: String, email: String, pwd: String, parallel: Boolean): Unit  = {
    val user = service match {
      case "-gmusic" => UserGoogleMusic(email, pwd)
      case "-spotify" => UserSpotify(email, pwd)
    }

    val account = Incomplete(user)
    val selenium = Selenium()
    val signInService = service match {
      case "-gmusic" => GoogleMusicSignIn(account, selenium)
      case "-spotify" => SpotifySignIn(account, selenium)
    }

    signInService.run()
    val downloader: Downloader = signInService.getDownloader
    if (parallel) {
      val futurePlaylists = downloader.getPlaylistsParallel
      val futureRes: Option[Future[List[Option[Playlist]]]] = futurePlaylists map {(list: List[Future[Option[Playlist]]]) =>
        list.foldLeft(Future(List.empty[Option[Playlist]])){(acc: Future[List[Option[Playlist]]], elm: Future[Option[Playlist]]) =>
          acc flatMap {list: List[Option[Playlist]] => elm map {element: Option[Playlist] => element :: list}}
        }
      }

      futureRes map {
        futureList => futureList onComplete {
          case Success(list) =>
            val playlists: List[Playlist] = list.flatten
            val completeAccount = Complete(user, playlists)
            println(JsUtil.toJson(completeAccount))
          case Failure(e) => println("Something went wrong!")
        }
      }
    } else {
      val playlists: Option[List[Option[Playlist]]] = downloader.getPlaylists
      val completeAccount = playlists map {_.flatten} map {Complete(user, _)}
      println(JsUtil.toJson(completeAccount))
    }

  }


}

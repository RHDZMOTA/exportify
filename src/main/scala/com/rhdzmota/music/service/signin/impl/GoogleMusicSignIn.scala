package com.rhdzmota.music.service.signin.impl

import com.rhdzmota.music.driver.Selenium
import com.rhdzmota.music.models.GoogleMusic
import com.rhdzmota.music.models.user.Account
import com.rhdzmota.music.service.signin.SignIn

case class GoogleMusicSignIn(account: Account, selenium: Selenium) extends SignIn with GoogleMusic
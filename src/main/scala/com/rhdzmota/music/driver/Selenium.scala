package com.rhdzmota.music.driver

import org.openqa.selenium.chrome.ChromeDriver

case class Selenium() {

  val getProjectDir: String = System.getProperty("user.dir")
  val driverPath: String = getProjectDir + "/src/resources/drivers/linux/chromedriver"

  def getDriver: ChromeDriver = {
    System.setProperty("webdriver.chrome.driver", driverPath)
    new ChromeDriver()
  }

  val browser: ChromeDriver = getDriver

}



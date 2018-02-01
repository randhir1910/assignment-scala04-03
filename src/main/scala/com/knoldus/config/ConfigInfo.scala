package com.knoldus.config

import com.typesafe.config._

object ConfigInfo {

  val ConsumerKey = ConfigFactory.load().getString("twitter.key.Consumer-Key")
  val ConsumerSecret = ConfigFactory.load().getString("twitter.key.Consumer-Secret")
  val AccessToken = ConfigFactory.load().getString("twitter.key.Access-Token")
  val AccessTokenSecret = ConfigFactory.load().getString("twitter.key.Access-Token-Secret")
}

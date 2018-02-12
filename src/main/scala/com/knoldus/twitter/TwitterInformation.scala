package com.knoldus.twitter

import com.knoldus.config.ConfigInfo
import com.knoldus.constant.Const
import twitter4j.auth.AccessToken
import twitter4j.{Query, Status, TwitterFactory}

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TwitterInformation {

  val twitter = new TwitterFactory().getInstance()
  twitter.setOAuthConsumer(ConfigInfo.ConsumerKey, ConfigInfo.ConsumerSecret)
  twitter.setOAuthAccessToken(new AccessToken(ConfigInfo.AccessToken, ConfigInfo.AccessTokenSecret))

  /**
    * methods to get information from #Randhir twitter.
    *
    * @param  hashTag
    * @return twitter information
    */
  private def tweets(hashTag: String): List[Status] = {

    val query = new Query(hashTag)
    query.setCount(Const.Hundred)
    val result = twitter.search(query)
    val startDate = "2018-01-09"
    // val oldDate = LocalDate.parse(startDate)
    val endDate = "2018-02-09"
    //  val newDate = LocalDate.parse(endDate)
    query.setSince(startDate)
    query.setUntil(endDate)
    // val dateDiff: Double = newDate.toEpochDay - oldDate.toEpochDay() + 1
    result.getTweets().asScala.toList

  }

  def getTotalTweets(hashTag: String): Future[List[String]] = Future {

    tweets(hashTag).map(status => status.getText)
  }

  def getAverageTweets(hashTag: String): Future[Double] = Future {

    val tweet = tweets(hashTag)
    tweet.size / 31.0
    // tweet.map(tweets => tweets.getCreatedAt).length / 31.0
  }

  def getAverageLikeCount(hashTag: String): Future[Double] = Future {

    val tweet = tweets(hashTag).map(tweet => tweet.getFavoriteCount).sum
    tweet / 31.0

  }

  def getAverageReTweetCount(hashTag: String): Future[Double] = Future {

    val tweet = tweets(hashTag).map(tweet => tweet.getRetweetCount).sum
    tweet / 31.0

  }
}

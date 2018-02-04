package com.knoldus.twitter

import com.knoldus.config.ConfigInfo
import com.knoldus.const.Const
import twitter4j.auth.AccessToken
import twitter4j.{Query, Status, TwitterFactory}

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TwitterInfo {

  val twitter = new TwitterFactory().getInstance()
  twitter.setOAuthConsumer(ConfigInfo.ConsumerKey, ConfigInfo.ConsumerSecret)
  twitter.setOAuthAccessToken(new AccessToken(ConfigInfo.AccessToken, ConfigInfo.AccessTokenSecret))

  /**
    * Query setup and get data from twitter.
    *
    * @param hashTag
    * @return
    */
  private def tweets(hashTag: String): List[Status] = {

    val query = new Query(hashTag)
    query.setCount(Const.Hundred)
    val result = twitter.search(query)
    query.setSince("2018-01-01")
    query.setUntil("2018-02-01")
    result.getTweets().asScala.toList

  }

  def getTotalTweets(hashTag: String): Future[Int] = {

    val tweets = new TwitterInfo().tweets(hashTag)

    Future {
      tweets.size
    }

  }

  def getAverageTweets(hashTag: String): Future[Double] = {

    val tweets = new TwitterInfo().tweets(hashTag)

    Future {
      tweets.size / 31.0
    }
  }

  def getAverageLikeCount(hashTag: String): Future[Double] = {

    val tweets = new TwitterInfo().tweets(hashTag).map(tweet => tweet.getFavoriteCount).sum

    Future {
      tweets / 31.0
    }

  }

  def getAverageReTweetCount(hashTag: String): Future[Double] = {

    val tweets = new TwitterInfo().tweets(hashTag).map(tweet => tweet.getRetweetCount).sum

    Future {
      tweets / 31.0
    }

  }
}

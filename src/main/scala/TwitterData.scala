import com.knoldus.config.ConfigInfo
import com.knoldus.constant.Const
import com.knoldus.twitter.TwitterInformation
import org.apache.log4j.Logger
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}


object TwitterData {

  def main(args: Array[String]): Unit = {

    val twitter = new TwitterFactory().getInstance()
    twitter.setOAuthConsumer(ConfigInfo.ConsumerKey, ConfigInfo.ConsumerSecret)
    twitter.setOAuthAccessToken(new AccessToken(ConfigInfo.AccessToken, ConfigInfo.AccessTokenSecret))
    val logger = Logger.getLogger(this.getClass)

    val twitterData = new TwitterInformation
    logger.info(" Enter HashTag to get Tweet information :- ")
    val hashTag = readLine()

    val totalTweet = twitterData.getTotalTweets(hashTag)
    val averageTweet = twitterData.getAverageTweets(hashTag)
    val averageLike = twitterData.getAverageLikeCount(hashTag)
    val averageReTweet = twitterData.getAverageReTweetCount(hashTag)

    logger.info("\n")

    Thread.sleep(Const.TenThousand)

    totalTweet andThen {

      case Success(result) => logger.info("Total Tweets " + result + "\n")
      case Failure(exception) => logger.info(exception)
    }

    averageTweet andThen {

      case Success(result) => logger.info("Average Tweets per day " + result + "\n")
      case Failure(exception) => logger.info(exception)
    }

    averageLike andThen {

      case Success(result) => logger.info("Average Like  per day   " + result + "\n")
      case Failure(exception) => logger.info(exception)
    }

    averageReTweet andThen {

      case Success(result) => logger.info("Average ReTweet per day " + result + "\n")
      case Failure(exception) => logger.info(exception)
    }

  }
}

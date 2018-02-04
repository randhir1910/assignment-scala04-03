import com.knoldus.config.ConfigInfo
import com.knoldus.twitter.TwitterInfo
import org.apache.log4j.Logger
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object TwitterData extends App {

  val Twitter = new TwitterFactory().getInstance()
  Twitter.setOAuthConsumer(ConfigInfo.ConsumerKey, ConfigInfo.ConsumerSecret)
  Twitter.setOAuthAccessToken(new AccessToken(ConfigInfo.AccessToken, ConfigInfo.AccessTokenSecret))
  val Printer = Logger.getLogger(this.getClass)

  val TwitterData = new TwitterInfo
  Printer.info(" Enter HashTag to get information :- ")
  val HashTag = readLine()

  val TotalTweet = TwitterData.getTotalTweets(HashTag)
  val AverageTweet = TwitterData.getAverageTweets(HashTag)
  val AverageLike = TwitterData.getAverageLikeCount(HashTag)
  val AverageReTweet = TwitterData.getAverageReTweetCount(HashTag)
  val TwitterInfoList = List(TotalTweet, AverageTweet, AverageLike, AverageReTweet)

  val TwitterInfo = Future.sequence(TwitterInfoList)

  Printer.info("\n")

  TwitterInfoList(0) andThen {

    case Success(result) => Printer.info("Total Tweets " + result + "\n")
    case Failure(exception) => Printer.info(exception)
  }

  TwitterInfoList(1) andThen {

    case Success(result) => Printer.info("Average Tweets " + result + "\n")
    case Failure(exception) => Printer.info(exception)
  }

  TwitterInfoList(2) andThen {

    case Success(result) => Printer.info("Average Tweets Like  " + result + "\n")
    case Failure(exception) => Printer.info(exception)
  }

  TwitterInfoList(3) andThen {

    case Success(result) => Printer.info("Average ReTweet Count " + result + "\n")
    case Failure(exception) => Printer.info(exception)
  }

}

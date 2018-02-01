import com.knoldus.config.ConfigInfo
import org.apache.log4j.Logger
import twitter4j.auth.AccessToken
import twitter4j.{Query, TwitterFactory}

object TwitterData extends App {

  val logger = Logger.getLogger(this.getClass)


  val twitter = new TwitterFactory().getInstance()
  twitter.setOAuthConsumer(ConfigInfo.ConsumerKey, ConfigInfo.ConsumerSecret)
  twitter.setOAuthAccessToken(new AccessToken(ConfigInfo.AccessToken, ConfigInfo.AccessTokenSecret))
  val statuses = twitter.getHomeTimeline

  val query = new Query("this is program testing tweet...")
  val result = twitter.search(query)

  val tweets = result.getTweets
  println()
  println("hello  " + tweets.size())
}

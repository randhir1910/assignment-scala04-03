import com.typesafe.config._

object ConfigInfo {

  val ConsumerKey = ConfigFactory.load().getString("my.secret.Consumer-Key")
  val ConsumerSecret = ConfigFactory.load().getString("my.secret.Consumer-Secret")
  val AccessToken = ConfigFactory.load().getString("my.secret.Access-Token")
  val AccessTokenSecret = ConfigFactory.load().getString("my.secret.Access-Token-Secret")
}

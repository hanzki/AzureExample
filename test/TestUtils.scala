import play.api.inject.guice.GuiceApplicationBuilder

/**
  * Created by hanzki on 13/08/16.
  */
object TestUtils {

  def testApplication = new GuiceApplicationBuilder()
    .configure("play.slick.db.config" -> "test.slick.dbs")
    .build

}

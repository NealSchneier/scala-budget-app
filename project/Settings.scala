import sbt._

object Settings {
  private lazy val versions = new {
    val scalaLogging = "3.5.0"
    val specs2 = "4.10.0"
    val finatra = "21.2.0"
  }

  private lazy val orgs = new {
    val scalaLogging = "com.typesafe.scala-logging"
    val twitter = "com.twitter"
  }
  lazy val loggingDeps = Seq(
    orgs.scalaLogging % "scala-logging_2.11" % versions.scalaLogging
  )

  lazy val unitTestDeps = Seq (
    "org.scalatest" %% "scalatest" % "3.2.2" % Test,
    "org.scalactic" %% "scalactic" % "3.2.2",
    "org.scalatest" %% "scalatest" % "3.2.2" % Test,
    "org.specs2" %% "specs2-core" % versions.specs2 % "test",
    "org.specs2" %% "specs2-mock" % versions.specs2 % "test",
    "com.twitter" %% "finatra-http" % "21.2.0" % "test" classifier "tests",
    "com.twitter" %% "finatra-jackson" % "21.2.0" % "test" classifier "tests",
    "com.twitter" %% "inject-server" % "21.2.0" % "test" classifier "tests",
    "com.twitter" %% "inject-app" % "21.2.0" % "test" classifier "tests",
    "com.twitter" %% "inject-core" % "21.2.0" % "test" classifier "tests",
    "com.twitter" %% "inject-modules" % "21.2.0" % "test" classifier "tests",
    "com.twitter" %% "finatra-http" % "21.2.0" % "test" ,
    "com.twitter" %% "finatra-jackson" % "21.2.0" % "test" ,
    "com.twitter" %% "inject-server" % "21.2.0" % "test",
    "com.twitter" %% "inject-app" % "21.2.0" % "test" ,
    "com.twitter" %% "inject-core" % "21.2.0" % "test" ,
    "com.twitter" %% "inject-modules" % "21.2.0" % "test"
  )

  lazy val finatraDeps = Seq(
    (orgs.twitter ) %% "finatra-http" % "21.2.0"
  )

}
name := """medtalkserver"""

version := "0.1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test,

  "org.postgresql" % "postgresql" % "9.4.1208.jre7",
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",
  "com.github.t3hnar" %% "scala-bcrypt" % "2.5",

  "org.webjars" % "jquery" % "2.2.1",
  "org.webjars" % "foundation" % "6.2.0"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

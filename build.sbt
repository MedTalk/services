name := """medtalkserver"""

version := "0.1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,

  "org.postgresql" % "postgresql" % "9.4.1208.jre7",
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",
  "com.github.t3hnar" %% "scala-bcrypt" % "2.5",
  "com.pauldijou" %% "jwt-play" % "0.7.0",
  "com.adrianhurt" %% "play-bootstrap" % "1.0-P25-B3"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

pipelineStages := Seq(digest, gzip)

maintainer in Docker := "Kha Nguyen <nlhkha@gmail.com>"

dockerExposedPorts := Seq(9000)

dockerUpdateLatest := true

dockerRepository := Some("medtalk")

import Dependencies._

name := "kafka_test"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += Resolver.bintrayRepo("cakesolutions", "maven")

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % versions.slf4j,
  "org.slf4j" % "log4j-over-slf4j" % versions.slf4j,
  "net.cakesolutions" %% "scala-kafka-client-akka" % versions.kafkaClient,
  "org.scalatest" %% "scalatest" % versions.scalaTest % "test",
  "net.cakesolutions" %% "scala-kafka-client-testkit" % versions.kafkaClient % "test"
)
import Dependencies._

name := "kafka_client_examples"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.8"

resolvers += Resolver.bintrayRepo("cakesolutions", "maven")

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % versions.slf4j,
  "org.slf4j" % "log4j-over-slf4j" % versions.slf4j,
  "ch.qos.logback" % "logback-classic" % versions.logback,
  "com.typesafe.akka" %% "akka-slf4j" % versions.akka,
  "net.cakesolutions" %% "scala-kafka-client-akka" % versions.kafkaClient,
  "org.scalatest" %% "scalatest" % versions.scalaTest % "test",
  "net.cakesolutions" %% "scala-kafka-client-testkit" % versions.kafkaClient % "test"
)
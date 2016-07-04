package net.cakesolutions.kafkatest

import akka.actor.ActorSystem
import org.slf4j.LoggerFactory

object Main extends App {
  val log = LoggerFactory.getLogger("IngestionMonolithApp")

  val system = ActorSystem("kafka_test")
}

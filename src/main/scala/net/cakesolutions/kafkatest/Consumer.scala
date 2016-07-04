package net.cakesolutions.kafkatest

import akka.actor.Actor
import cakesolutions.kafka.KafkaConsumer
import cakesolutions.kafka.akka.KafkaConsumerActor.Confirm
import cakesolutions.kafka.akka.{ConsumerRecords, KafkaConsumerActor}
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

object Consumer {
  val log = LoggerFactory.getLogger("Consumer")

  val consumerConf = KafkaConsumer.Conf(
    new StringDeserializer,
    new StringDeserializer,
    bootstrapServers = "192.168.99.100:9092",
    groupId = "groupId",
    enableAutoCommit = false)

  val actorConf = KafkaConsumerActor.Conf(List("topic1"), 1.seconds, 3.seconds)
}

class Consumer extends Actor {
  import Consumer._

  val recordsExt = ConsumerRecords.extractor[Int, String]

  override def receive: Receive = {
    case recordsExt(records) =>
      processRecords(records.pairs)
      sender() ! Confirm(records.offsets)
  }

  private def processRecords(records: Seq[(Option[Int], String)]) = {
    log.info("R:" + records.map(_._2))
  }

  val consumer = context.actorOf(
    KafkaConsumerActor.props(consumerConf, actorConf, self)
  )
}

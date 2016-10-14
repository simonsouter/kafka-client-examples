package net.cakesolutions.kafka.examples

import akka.actor.{Actor, Props}
import cakesolutions.kafka.KafkaConsumer
import cakesolutions.kafka.akka.KafkaConsumerActor.{Confirm, Subscribe}
import cakesolutions.kafka.akka.{ConsumerRecords, KafkaConsumerActor, Offsets}
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

object ConsumerSelfManaged {
  val log = LoggerFactory.getLogger("Consumer")

  val consumerConf = KafkaConsumer.Conf(
    new StringDeserializer,
    new StringDeserializer,
    bootstrapServers = "192.168.99.100:9092",
    groupId = "groupId",
    enableAutoCommit = false,
    autoOffsetReset = OffsetResetStrategy.EARLIEST)

  val actorConf = KafkaConsumerActor.Conf(1.seconds, 10.seconds)

  def props: Props = {
    Props(new ConsumerSelfManaged)
  }
}

class ConsumerSelfManaged extends Actor {

  import ConsumerSelfManaged._

  val recordsExt = ConsumerRecords.extractor[String, String]

  override def receive: Receive = {
    case recordsExt(records) =>
      log.info("Records: count:" + records.size + "::::" + records.offsets + ":::" + records.pairs.map(_._2))

      if (Math.random() >= 0.0) {
        log.info("!!OK!")
        processRecords(records.pairs)
        sender() ! Confirm(records.offsets, commit = false)
      } else {
        log.info("!!!!!")
      }
    case a =>
      log.info("WTF:" + a)
  }

  private def processRecords(records: Seq[(Option[String], String)]) = {
    Thread.sleep(5000)
  }

  val consumer = context.actorOf(
    KafkaConsumerActor.props(consumerConf, actorConf, self)
  )

  consumer ! Subscribe.ManualOffset(Offsets(Map((new TopicPartition("self", 0),1))))
  context.watch(consumer)
}

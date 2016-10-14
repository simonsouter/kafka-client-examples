package net.cakesolutions.kafkatest

import akka.actor.{Actor, ActorSystem, Props}
import cakesolutions.kafka.KafkaConsumer
import cakesolutions.kafka.akka.KafkaConsumerActor.{Confirm, Subscribe}
import cakesolutions.kafka.akka.{ConsumerRecords, KafkaConsumerActor}
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

object ConsumerTestBoot extends App {
  ConsumerTest

  val system = ActorSystem("kafka_test")
  system.actorOf(ConsumerTest.props("1"), "A1")
  system.actorOf(ConsumerTest.props("2"), "A2")
}

object ConsumerTest {
  val log = LoggerFactory.getLogger(ConsumerTest.getClass.getName)

  def consumerConf(g: String) = KafkaConsumer.Conf(
    new StringDeserializer,
    new StringDeserializer,
    bootstrapServers = "127.0.0.1:9092",
    groupId = g,
    enableAutoCommit = false,
    autoOffsetReset = OffsetResetStrategy.EARLIEST)

  val actorConf = KafkaConsumerActor.Conf(1.seconds, 10.seconds)

  def props(g: String): Props = Props(new ConsumerTest(g))
}

class ConsumerTest(g: String) extends Actor {

  import ConsumerTest._

  val recordsExt = ConsumerRecords.extractor[String, String]

  override def receive: Receive = {
    case recordsExt(records) =>
      log.info("Records: count:" + records.size + "::::" + records.offsets + ":::" + records.pairs.map(_._2))

      if (Math.random() > 0.0) {
        log.info("!!OK!")
        processRecords(records.pairs)

        processRecords(records.records)
        sender() ! Confirm(records.offsets, commit = true)
      } else {
        log.info("!!!!!")
      }
    case a =>
      log.info("WTF:" + a)
  }

  private def processRecords(records: Seq[(Option[String], String)]) = {
    import cakesolutions.kafka.akka.KafkaConsumerActor._
    Subscribe.AutoPartition(Seq("t"))

    Thread.sleep(5000)
  }

  private def processRecords(records: org.apache.kafka.clients.consumer.ConsumerRecords[String, String]) = {
    Thread.sleep(5000)
  }

  val consumer = context.actorOf(
    KafkaConsumerActor.props(consumerConf(g), actorConf, self)
  )

  consumer ! Subscribe.AutoPartition(List("test"))
//  consumer ! Subscribe(Some(Offsets(Map((new TopicPartition("actions", 0),155), (new TopicPartition("actions", 1), 105)))))
}

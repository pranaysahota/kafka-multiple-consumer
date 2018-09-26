package io.dl.kafka.service;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import io.dl.kafka.utils.Constants;
import io.dl.kafka.utils.Utils;

@Service
public class ConsumerService {

	CountDownLatch cdl1 = new CountDownLatch(2);
	CountDownLatch cdl2 = new CountDownLatch(2);

	static int count_c1 = 0;
	static int count_c2 = 0;

	@KafkaListener(topicPartitions = { @TopicPartition(topic = Constants.TOPIC, partitions = { "0" }) })
	public void consumeMessageFromPartition0(String message) {
		Utils.logger.info("Thread ID: " + Thread.currentThread().getId() + ", Received Message: " + message);
		Utils.logger.info("message_count_cg2_c1: " + count_c1++);
		cdl1.countDown();
	}

	@KafkaListener(topicPartitions = { @TopicPartition(topic = Constants.TOPIC, partitions = { "1" }) })
	public void consumeMessageFromPartition1(String message) {
		Utils.logger.info("Thread ID: " + Thread.currentThread().getId() + ", Received Message: " + message);
		Utils.logger.info("message_count_cg2_c2: " + count_c2++);
		cdl2.countDown();
	}
}

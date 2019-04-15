package com.it.test.nkia.app.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderReceiever {

	@KafkaListener(topics = "test", groupId = "group_id")
	public void consume(String message) {
		log.info(String.format("$$ -> Consumed Message -> %s", message));
	}
}

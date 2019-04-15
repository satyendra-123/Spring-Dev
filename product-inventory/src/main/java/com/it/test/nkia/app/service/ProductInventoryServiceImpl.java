package com.it.test.nkia.app.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.test.nkia.app.entity.Order;
import com.it.test.nkia.app.exception.ProductInventoryException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductInventoryServiceImpl implements ProductService {

	private static final String filePath = "src/main/resources/invoices/invoice.json";
	
	private static final String TOPIC = "test";
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Override
	@Retryable(include = ProductInventoryException.class, maxAttempts = 2)
	public void generateInvoice(Order order) {
		if (null == order) {
			throw new ProductInventoryException("order is null hence invoice could not be generated");
		} else {
			try {
				File file = new File(filePath);
				BufferedWriter bufWr = new BufferedWriter(new FileWriter(file));
				ObjectMapper Obj = new ObjectMapper();
				bufWr.write(Obj.writeValueAsString(order));
				bufWr.flush();
				bufWr.close();
				sendMessage("order has been placed for order id "+order.getOrderId());
			} catch (IOException e) {
				throw new ProductInventoryException(e.getMessage());
			}
		}
	}

	public void sendMessage(String message) {
		log.info(String.format("$$ -> Producing message --> %s", message));
		this.kafkaTemplate.send(TOPIC, message);
	}
	
}

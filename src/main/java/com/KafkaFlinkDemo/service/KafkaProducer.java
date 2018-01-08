package com.KafkaFlinkDemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.KafkaFlinkDemo.dataSource.LiveStockDataFetcher;
import com.KafkaFlinkDemo.model.LiveStockQuote;
import com.KafkaFlinkDemo.properties.ApplicationProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaProducer {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	LiveStockDataFetcher liveStockDataFetcher;

	@Autowired
	ApplicationProperties props;

	public void sendLiveStockQuotesToKafka(LiveStockQuote quote) throws InterruptedException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		kafkaTemplate.send(props.getKafkaTopicStkQuotes(), mapper.writeValueAsString(quote));
	}
}

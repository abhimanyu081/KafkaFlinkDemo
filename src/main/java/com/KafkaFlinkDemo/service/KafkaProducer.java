package com.KafkaFlinkDemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.KafkaFlinkDemo.model.StockQuoteDto;
import com.KafkaFlinkDemo.properties.ApplicationProperties;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class KafkaProducer {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, StockQuoteDto> kafkaTemplate;

	@Autowired
	ApplicationProperties props;

	public void sendLiveStockQuotesToKafka(StockQuoteDto quote) throws InterruptedException, JsonProcessingException {
		kafkaTemplate.send(props.getKafkaTopicStkQuotes(), quote);
		LOG.info("Stock quote sent to kafka with symbol {} and time {}",quote.getSymbol(), quote.getDateTime());
	}
}

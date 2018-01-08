package com.KafkaFlinkDemo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KafkaFlinkDemo.dataSource.LiveStockDataFetcher;
import com.KafkaFlinkDemo.model.LiveStockQuote;
import com.KafkaFlinkDemo.properties.ApplicationProperties;
import com.KafkaFlinkDemo.service.FlinkConsumer;
import com.KafkaFlinkDemo.service.KafkaProducer;
import com.KafkaFlinkDemo.storage.MessageStorage;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class WebRestController {
	
	
	@Autowired
	MessageStorage storage;
	
	@Autowired
	LiveStockDataFetcher dataFetcher;
	
	@Autowired
	private KafkaProducer producer;
	
	
	@Autowired
	ApplicationProperties props;
	
	@Autowired FlinkConsumer flinkConsumer;
	
	
	
	
	@RequestMapping(value="/consumer")
	public String getAllRecievedMessage() throws Exception{
		flinkConsumer.createStockOhlcByInterval(1);
		return "OK";
	}
	
	
	@RequestMapping(value="/flink/consumer")
	public String getFlinkMessage() throws Exception{
		
		
		return "OK";
	}
	
	@RequestMapping(value="/producer")
	public String loadStockQuotes() throws JsonProcessingException, InterruptedException { 
		List<LiveStockQuote> list = dataFetcher.callApiForIntradayQuotes1Min("AAPL");
		System.out.println(list.size());
		for(LiveStockQuote stk : list) {
			//Thread.sleep(500);
			producer.sendLiveStockQuotesToKafka(stk);
		}
		return "DONE";
	}
}

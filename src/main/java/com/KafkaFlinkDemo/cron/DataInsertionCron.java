package com.KafkaFlinkDemo.cron;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.KafkaFlinkDemo.dataSource.LiveStockDataFetcher;
import com.KafkaFlinkDemo.model.LiveIndexDataDto;
import com.KafkaFlinkDemo.model.LiveStockQuote;
import com.KafkaFlinkDemo.model.StockQuoteDto;
import com.KafkaFlinkDemo.service.KafkaProducer;
import com.KafkaFlinkDemo.service.MongoDataService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class DataInsertionCron {
	
	private Logger LOG = LoggerFactory.getLogger(getClass().getSimpleName());
	
	String SYMBOLS = "AAPL,SMG,PDS,SYK,DSU,NMM,MUG,NGVC,MSFT,FB";

	@Autowired
	LiveStockDataFetcher dataFetcher;

	@Autowired
	private KafkaProducer producer;
	
	@Autowired
	MongoDataService mongo;

	
	//@Scheduled(cron="0/30 * * * * ?")
	public void loadStockQuotes() throws JsonProcessingException, InterruptedException {
		
		LOG.info("data insetion cron started ......");
		List<LiveStockQuote> list = dataFetcher.callApiForIntradayQuotes1Min(SYMBOLS);
		LOG.info("{} records fetched from API for AAPL",list.size());
		for (LiveStockQuote stk : list) {
			StockQuoteDto quote = new StockQuoteDto(stk.getSymbol(), stk.getPrice(), stk.getVolume2(), stk.getTimestamp2());
			producer.sendLiveStockQuotesToKafka(quote);
		}
		LOG.info("Data sent to KAFKA ......");
	}
	
	@Scheduled(cron="0/30 * * * * ?")
	public void loadNSEStockQuotes() throws JsonProcessingException, InterruptedException {
		
		LOG.info("data insetion NSE cron started ......");
		List<LiveIndexDataDto> list = mongo.findAll();
		LOG.info("{} records fetched from Monogo",list.size());
		for (LiveIndexDataDto stk : list) {
			producer.sendLiveStockQuotesToKafka(stk);
		}
		LOG.info("Data sent to KAFKA ......");
	}
}

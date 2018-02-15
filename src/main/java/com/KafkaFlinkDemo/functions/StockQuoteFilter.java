package com.KafkaFlinkDemo.functions;

import org.apache.flink.api.common.functions.RichFilterFunction;

import com.KafkaFlinkDemo.model.LiveStockQuote;

public class StockQuoteFilter extends RichFilterFunction<LiveStockQuote>{

	@Override
	public boolean filter(LiveStockQuote value) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	

}

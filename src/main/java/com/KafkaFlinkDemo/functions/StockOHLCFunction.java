package com.KafkaFlinkDemo.functions;

import org.apache.flink.api.common.functions.AggregateFunction;

import com.KafkaFlinkDemo.model.LiveStockQuote;
import com.KafkaFlinkDemo.model.StockQuoteDto;

public class StockOHLCFunction implements AggregateFunction<LiveStockQuote, StockQuoteDto, StockQuoteDto>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public StockQuoteDto createAccumulator() {
		System.out.println("Create acculatetor called");
		return new StockQuoteDto();
	}

	@Override
	public StockQuoteDto add(LiveStockQuote value, StockQuoteDto accumulator) {
		int count =0;
		if(count==0) {
			accumulator.setOpen(value.getOpen());
		}
		try {
			if(value.getHigh().compareTo(accumulator.getHigh())==1) {
				System.out.println("in if high");
				accumulator.setHigh(value.getHigh());
			}
			
			if(value.getLow().compareTo(accumulator.getLow())==-1) {
				System.out.println("in if low");
				accumulator.setLow(value.getLow());
			}
			accumulator.setClose(value.getClose());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accumulator;
		
	}

	@Override
	public StockQuoteDto getResult(StockQuoteDto accumulator) {
		System.out.println("returned acc = "+accumulator);
		return accumulator;
	}

	@Override
	public StockQuoteDto merge(StockQuoteDto a, StockQuoteDto b) {
		System.out.println("get marge called ");
		return a;
	}
	
	

}

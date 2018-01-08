package com.KafkaFlinkDemo.functions;

import org.apache.flink.api.common.functions.AggregateFunction;

import com.KafkaFlinkDemo.model.LiveStockQuote;
import com.KafkaFlinkDemo.model.StockOHLC;

public class StockOHLCFunction implements AggregateFunction<LiveStockQuote, StockOHLC, StockOHLC>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public StockOHLC createAccumulator() {
		System.out.println("Create acculatetor called");
		return new StockOHLC();
	}

	@Override
	public StockOHLC add(LiveStockQuote value, StockOHLC accumulator) {
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
	public StockOHLC getResult(StockOHLC accumulator) {
		System.out.println("returned acc = "+accumulator);
		return accumulator;
	}

	@Override
	public StockOHLC merge(StockOHLC a, StockOHLC b) {
		System.out.println("get marge called ");
		return a;
	}
	
	

}

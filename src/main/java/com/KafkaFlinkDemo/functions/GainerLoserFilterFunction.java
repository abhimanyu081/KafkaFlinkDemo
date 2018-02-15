package com.KafkaFlinkDemo.functions;

import java.math.BigDecimal;

import org.apache.flink.api.common.functions.RichFilterFunction;

import com.KafkaFlinkDemo.model.StockQuoteDto;

public class GainerLoserFilterFunction extends RichFilterFunction<StockQuoteDto>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean filter(StockQuoteDto value) throws Exception {
		if(value.getPrice().compareTo(BigDecimal.ZERO)==1) {
			return true;
		}
		return false;
	}

}

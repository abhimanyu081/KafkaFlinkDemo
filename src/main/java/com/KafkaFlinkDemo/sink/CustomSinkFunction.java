package com.KafkaFlinkDemo.sink;

import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import com.KafkaFlinkDemo.model.StockOHLC;

public class CustomSinkFunction implements SinkFunction<StockOHLC>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void invoke(StockOHLC value, Context context) throws Exception {
		// TODO Auto-generated method stub
		SinkFunction.super.invoke(value, context);
	}

}

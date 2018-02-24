package com.KafkaFlinkDemo.functions;

import org.apache.flink.api.common.functions.RichFilterFunction;

import com.KafkaFlinkDemo.model.LiveIndexDataDto;

public class EquityStatsFilterFunction extends RichFilterFunction<LiveIndexDataDto>{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean filter(LiveIndexDataDto value) throws Exception {
		if(value!=null) {
			Double perChange = Double.valueOf(value.getPercentChange());
			if(perChange>0)
			return true;
		}
		return false;
	}

}

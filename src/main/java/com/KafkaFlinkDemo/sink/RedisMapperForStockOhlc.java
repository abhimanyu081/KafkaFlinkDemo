package com.KafkaFlinkDemo.sink;

import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

import com.KafkaFlinkDemo.model.StockOHLC;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisMapperForStockOhlc implements RedisMapper<StockOHLC>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public RedisCommandDescription getCommandDescription() {
		  return new RedisCommandDescription(RedisCommand.ZADD, "StockOHLC");
	}

	@Override
	public String getKeyFromData(StockOHLC data) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getValueFromData(StockOHLC data) {
		return data.getClose().toString();
	}
}

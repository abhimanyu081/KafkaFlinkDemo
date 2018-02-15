package com.KafkaFlinkDemo.sink;

import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

import com.KafkaFlinkDemo.model.StockQuoteDto;
import com.KafkaFlinkDemo.util.SerializationUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

public class RedisMapperForStockQuote implements RedisMapper<StockQuoteDto>{
	
	private String key;
	private RedisCommand command;

	
	public RedisMapperForStockQuote(String key,RedisCommand command ) {
		super();
		this.key = key;
		this.command=command;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public RedisCommandDescription getCommandDescription() {
		  //return new RedisCommandDescription(RedisCommand.ZADD, "StockOHLC");
		return new RedisCommandDescription(command, key);
	}

	@Override
	public String getKeyFromData(StockQuoteDto data) {
		try {
			return SerializationUtil.serializeToJsonString(data);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getValueFromData(StockQuoteDto data) {
		return String.valueOf(data.getPrice());
	}
}

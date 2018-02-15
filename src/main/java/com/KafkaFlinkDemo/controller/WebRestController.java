package com.KafkaFlinkDemo.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KafkaFlinkDemo.constants.StringConstants;
import com.KafkaFlinkDemo.model.StockQuoteDto;

@RestController
public class WebRestController {
	
	@Autowired
	RedisTemplate<String, StockQuoteDto> redisTemplate;
	
	@RequestMapping("/stats/gainers")
	public List<StockQuoteDto> getGainers(){
		List<StockQuoteDto> list = new ArrayList<>(redisTemplate.opsForZSet().range(StringConstants.REDIS_KEY_GAINERS, 0, -1));
		return list;
	}
	
	
	
	
	

}

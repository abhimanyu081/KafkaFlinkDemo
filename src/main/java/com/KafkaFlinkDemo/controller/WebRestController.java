package com.KafkaFlinkDemo.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KafkaFlinkDemo.constants.StringConstants;
import com.KafkaFlinkDemo.model.LiveIndexDataDto;
import com.KafkaFlinkDemo.service.MongoDataService;

@RestController
public class WebRestController {
	
	@Autowired
	RedisTemplate<String, LiveIndexDataDto> redisTemplate;
	
	@Autowired
	MongoDataService mongo;
	
	@RequestMapping("/stats/gainers")
	public List<LiveIndexDataDto> getGainers(){
		List<LiveIndexDataDto> list = new ArrayList<LiveIndexDataDto>(redisTemplate.opsForZSet().range(StringConstants.REDIS_KEY_GAINERS, 0, -1));
		return list;
	}
	
	@RequestMapping("/quote/nse")
	public List<LiveIndexDataDto> getQoutes(){
		return mongo.findAll().subList(0, 1);
	}
	
	
	
	
	

}

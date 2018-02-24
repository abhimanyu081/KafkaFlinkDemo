package com.KafkaFlinkDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.KafkaFlinkDemo.model.LiveIndexDataDto;

@Repository
public class MongoDataService {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<LiveIndexDataDto> findAll(){
		//mongoTemplate.findAll(LiveIndexDataDto.class, "nse_quotes");
		return mongoTemplate.find(new Query().limit(10000), LiveIndexDataDto.class, "nse_quotes");
		
	}
	
}

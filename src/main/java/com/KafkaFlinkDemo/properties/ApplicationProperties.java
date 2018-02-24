package com.KafkaFlinkDemo.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class ApplicationProperties {
	
	@Value("${data.api.key}")
	private String stockDataApiKey;
	
	
	@Value("${data.api}")
	private String dataApiUrl;
	
	@Value("${kafka.topic.live.stock}")
	private String kafkaTopicStkQuotes;
	
	
	
	@Value("${jsa.kafka.bootstrap-servers}")
	private String bootstrapServer;
	
	@Value("${jsa.kafka.consumer.group-id}")
	private String groupId;
	
	@Value("${redis.host}")
	private String redisHost;
	
	@Value("${redis.port}")
	private int redisPort;
	
	@Value("${redis.db}")
	private int redisDb;
	
	@Value("${mongo.host}")
	private String mongoHost;
	
	@Value("${mongo.port}")
	private String mongoPort;
	
	
	
	public String getMongoHost() {
		return mongoHost;
	}




	public String getMongoPort() {
		return mongoPort;
	}




	public String getRedisHost() {
		return redisHost;
	}




	public int getRedisPort() {
		return redisPort;
	}




	public int getRedisDb() {
		return redisDb;
	}




	public String getBootstrapServer() {
		return bootstrapServer;
	}




	public String getGroupId() {
		return groupId;
	}




	public String getKafkaTopicStkQuotes() {
		return kafkaTopicStkQuotes;
	}




	public String getStockDataApiKey() {
		return stockDataApiKey;
	}




	public String getDataApiUrl() {
		return dataApiUrl;
	}

	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}


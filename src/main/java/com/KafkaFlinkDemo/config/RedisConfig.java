package com.KafkaFlinkDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

	@Bean
	RedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		jedisConFactory.setHostName("localhost");
		jedisConFactory.setPort(6379);
		jedisConFactory.setDatabase(1);
		return jedisConFactory;
	}

	/*@Bean
	public RedisTemplate<String, StockQuoteDto> redisTemplateStockQuote() {
		RedisTemplate<String, StockQuoteDto> template = new RedisTemplate<String, StockQuoteDto>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(StockQuoteDto.class));
		template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(StockQuoteDto.class));
		return template;
	}*/
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}

	/*@Bean
	public RedisTemplate<String, LiveIndexDataDto> redisTemplateStockQuoteNSE() {
		RedisTemplate<String, LiveIndexDataDto> template = new RedisTemplate<String, LiveIndexDataDto>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(LiveIndexDataDto.class));
		template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(LiveIndexDataDto.class));
		return template;
	}*/
	
}

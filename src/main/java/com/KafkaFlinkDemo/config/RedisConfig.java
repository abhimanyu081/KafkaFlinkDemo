package com.KafkaFlinkDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;



//@Configuration
public class RedisConfig<T> {

	@Bean
	RedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	
	@Bean
	public RedisTemplate<String, T> redisTemplate() {
		RedisTemplate<String, T> template = new RedisTemplate<String, T>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}
	
	
}

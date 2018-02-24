package com.KafkaFlinkDemo.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;

import com.KafkaFlinkDemo.util.RedisUtil;

@Service
public class RedisService {

	private Logger LOG = LoggerFactory.getLogger(getClass().getSimpleName());

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	// START of ZSET Operations

	/***
	 * 
	 * 
	 * @param start
	 * @param rows
	 * @param key
	 * @param type
	 * @return
	 */

	public <T> Set<T> getSortedSetElements(int start, int rows, String key, Class<T> type) {
		int end = -1;

		if (rows > 0) {
			end = start + rows;
		}
		Set<Object> records = redisTemplate.opsForZSet().range(key, start, end);
		return RedisUtil.convertSet(records, type);
	}

	/**
	 * 
	 * @param key
	 * @param obj
	 * @param weight
	 * @param type
	 */
	public <T> void addToSortedSet(String key, T obj, Double weight, Class<T> type) {
		redisTemplate.opsForZSet().add(key, obj, weight);
	}

	/**
	 * 
	 * @param key
	 * @param elements
	 */
	public <T> void addAllToSortedSet(String key, Set<TypedTuple<Object>> elements) {
		redisTemplate.opsForZSet().add(key, elements);

	}

	/**
	 * 
	 * @param key
	 * @param elements
	 */
	public <T> void replaceSortedSet(String key, Set<TypedTuple<Object>> elements) {
		redisTemplate.opsForZSet().removeRange(key, 0, -1);
		redisTemplate.opsForZSet().add(key, elements);

	}

	// End of ZSET Operations

	public <T> Set<TypedTuple<T>> getGenericTypedTuple(Set<TypedTuple<T>> elements, Class<T> type) {
		if (elements != null && elements.size() > 0) {
			Set<TypedTuple<T>> genericTypedSet = new HashSet<TypedTuple<T>>(elements.size());
			for (TypedTuple<T> tuple : elements) {
				TypedTuple<T> tupleNew = (TypedTuple<T>) tuple;
				genericTypedSet.add(tupleNew);
			}
			return genericTypedSet;
		}
		return new HashSet<TypedTuple<T>>();
	}

	// START of HASH operations

	public <T> void addToHash(String key, T obj) {
		redisTemplate.opsForHash().putAll(key, RedisUtil.convertObjectToMap(obj));
	}
	
	
}

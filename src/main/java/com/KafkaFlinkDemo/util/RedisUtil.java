

package com.KafkaFlinkDemo.util;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisUtil {
	
	
	
	@SuppressWarnings("unchecked")
	public static <T> Map<String,Object> convertObjectToMap(T obj){
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> map = om.convertValue(obj,Map.class);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public static  <T> Set<T> convertSet(Set<Object> rows, Class<T> type) {
		if (rows != null && rows.size() > 0) {
			Set<T> genericRows = new HashSet<>();
			for (Object obj : rows) {
				T t = (T) obj;
				genericRows.add(t);
			}
			return genericRows;
		}
		return new HashSet<T>();

	}


}

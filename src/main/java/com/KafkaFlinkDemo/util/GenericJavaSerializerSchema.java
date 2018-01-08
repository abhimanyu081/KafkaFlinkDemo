package com.KafkaFlinkDemo.util;

import org.apache.flink.api.common.serialization.SerializationSchema;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericJavaSerializerSchema<T> implements SerializationSchema<T>{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	@Override
	public byte[] serialize(T element) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(element, String.class).getBytes();
		
	}
	

}

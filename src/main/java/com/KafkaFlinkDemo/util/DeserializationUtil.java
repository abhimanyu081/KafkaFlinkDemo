package com.KafkaFlinkDemo.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DeserializationUtil {
	

private static ObjectMapper objectMapper;
	
	/**
	 * TODO // Check if object mapper is thread sage
	 */
	
	static {
		
		objectMapper=new ObjectMapper();
	}

	public static <T>  T deserializeToObject(byte[] byteArr, Class<T> type) throws IOException {
		return objectMapper.readValue(byteArr, type);
	}

}

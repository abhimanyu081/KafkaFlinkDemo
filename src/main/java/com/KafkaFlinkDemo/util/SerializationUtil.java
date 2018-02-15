package com.KafkaFlinkDemo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class SerializationUtil {
	
	private static ObjectWriter objectWriter;
	
	/**
	 * TODO // Check if object mapper is thread sage
	 */
	
	static {
		objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	}

	public static <T>  String serializeToJsonString(T obj) throws JsonProcessingException {
		return objectWriter.writeValueAsString(obj);
	}
	
	public static <T>  byte[] serializeToByteArr(T obj) throws JsonProcessingException {
		return objectWriter.writeValueAsBytes(obj);
	}
}

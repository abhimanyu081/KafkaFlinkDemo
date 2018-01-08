package com.KafkaFlinkDemo.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationUtils {
	
	@SuppressWarnings("unchecked")
	public static <T> T deserializeByteArray(byte[] byteArr, Class<T> type) {
		 ObjectMapper mapper = new ObjectMapper();
		 
		try {
			T t = mapper.readValue(byteArr, type);
			return t;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
		
	}

}

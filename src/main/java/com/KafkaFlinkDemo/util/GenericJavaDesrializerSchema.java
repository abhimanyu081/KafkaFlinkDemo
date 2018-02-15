package com.KafkaFlinkDemo.util;

import java.io.IOException;

import org.apache.flink.api.common.serialization.AbstractDeserializationSchema;

public class GenericJavaDesrializerSchema<T> extends AbstractDeserializationSchema<T>{
	
	private Class<T> type;

	
	public GenericJavaDesrializerSchema(Class<T> type) {
		super();
		this.type = type;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public T deserialize(byte[] message) throws IOException {
		return DeserializationUtil.deserializeToObject(message,type);
	}

}

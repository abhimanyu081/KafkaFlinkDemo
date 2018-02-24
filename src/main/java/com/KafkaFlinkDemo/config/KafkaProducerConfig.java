package com.KafkaFlinkDemo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.KafkaFlinkDemo.properties.ApplicationProperties;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;


@Configuration
public class KafkaProducerConfig {
	
	@Autowired
	ApplicationProperties props;
	
	@Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configProps = new HashMap<String, Object>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, props.getBootstrapServer());
        //configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, new StringSerializer());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,new GenericJackson2JsonRedisSerializer());
        return new DefaultKafkaProducerFactory<String, Object>(configProps);
    }
    
	
	
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<String, Object>(producerFactory());
    }
    
   /* @Bean
    public KafkaTemplate<String, LiveIndexDataDto> kafkaTemplateLiveStockNse() {
    	Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, props.getBootstrapServer());
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
        return new KafkaTemplate<String, LiveIndexDataDto>(new DefaultKafkaProducerFactory<String, LiveIndexDataDto>(configProps));
    }*/
}

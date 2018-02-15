package com.KafkaFlinkDemo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.KafkaFlinkDemo.model.StockQuoteDto;
import com.KafkaFlinkDemo.properties.ApplicationProperties;


@Configuration
public class KafkaProducerConfig {
	
	@Autowired
	ApplicationProperties props;
	
	@Bean
    public ProducerFactory<String, StockQuoteDto> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, props.getBootstrapServer());
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
        return new DefaultKafkaProducerFactory<String, StockQuoteDto>(configProps);
    }
    
    @Bean
    public KafkaTemplate<String, StockQuoteDto> kafkaTemplateLiveStock() {
        return new KafkaTemplate<String, StockQuoteDto>(producerFactory());
    }
}

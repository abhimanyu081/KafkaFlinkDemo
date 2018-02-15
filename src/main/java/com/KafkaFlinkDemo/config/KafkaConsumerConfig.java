package com.KafkaFlinkDemo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


import com.KafkaFlinkDemo.model.StockQuoteDto;
import com.KafkaFlinkDemo.properties.ApplicationProperties;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	
	@Autowired
	ApplicationProperties applicationProperties;
 
	
    @Bean
    public ConsumerFactory<String, StockQuoteDto> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, applicationProperties.getBootstrapServer());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, applicationProperties.getGroupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, new JsonDeserializer<StockQuoteDto>(StockQuoteDto.class));
        JsonDeserializer<StockQuoteDto> jsonDeserializer = new JsonDeserializer<>(StockQuoteDto.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);

        //return new DefaultKafkaConsumerFactory<>(props);
    }
 
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, StockQuoteDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, StockQuoteDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
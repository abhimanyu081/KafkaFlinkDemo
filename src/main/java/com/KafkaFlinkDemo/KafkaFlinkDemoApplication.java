package com.KafkaFlinkDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableMongoRepositories
//@ComponentScan("com")
public class KafkaFlinkDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaFlinkDemoApplication.class, args);
	}
}

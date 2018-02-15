package com.KafkaFlinkDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@ComponentScan("com")
public class KafkaFlinkDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaFlinkDemoApplication.class, args);
	}
}

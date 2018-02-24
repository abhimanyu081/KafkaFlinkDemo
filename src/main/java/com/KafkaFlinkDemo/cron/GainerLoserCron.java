package com.KafkaFlinkDemo.cron;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.KafkaFlinkDemo.service.FlinkConsumer;

@Component
public class GainerLoserCron {
	
	@Autowired
	FlinkConsumer flinkConsumer;

	@Scheduled(cron="0/30 * * * * ?")
	public void calculateGainers() {
		System.out.println("GainerLoser Cron started at "+ new Date());
		try {
			flinkConsumer.getGainerLoserNse(1);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

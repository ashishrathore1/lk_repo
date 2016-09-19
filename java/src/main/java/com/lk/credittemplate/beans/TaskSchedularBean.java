package com.lk.credittemplate.beans;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskSchedularBean {

	private static final Logger logger = LoggerFactory.getLogger(TaskSchedularBean.class);

	// The method will run on 23:59:59 everyday. cron format: sec min hour day
	// mon week
	// @Scheduled(cron="59 * * * * *")
	// @Scheduled(fixedRate = 300000) // every 5 min call
	// @Scheduled(cron="0 */5 * * * ?") // every 5 min call
	public void runTask() throws ParseException {
		System.out.println("cron started");
	}

}

package com.sunbin.springboot.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {
	private int count = 0;
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

//	@Scheduled(cron = "*/5 * * * * ?")
	@Scheduled(cron = "${sunbin.schedule.task.cron}")
	private void process() {
		System.out.println("schedule task run:\t" + (++count) + " at " + sdf.format(new Date()));
	}

}

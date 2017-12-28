package com.zya.scheduleremind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScheduleremindApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleremindApplication.class, args);
	}
}

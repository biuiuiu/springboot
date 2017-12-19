package com.zya.zone;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ZoneApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ZoneApplication.class).web(true).run(args);
	}
}

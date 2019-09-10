package com.pm.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.pm"})
@EntityScan(basePackages = "com.pm.common.entities")
public class PocketMarketAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocketMarketAuthApplication.class, args);
	}
}

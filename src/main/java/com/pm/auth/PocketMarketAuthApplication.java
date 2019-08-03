package com.pm.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.pm.common.entities")
@ComponentScan(basePackages = {"com.pm"})
public class PocketMarketAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocketMarketAuthApplication.class, args);
	}
}

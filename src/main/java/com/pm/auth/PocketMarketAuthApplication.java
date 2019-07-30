package com.pm.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.pm.common.entities")
public class PocketMarketAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocketMarketAuthApplication.class, args);
	}
	/*
	 * @Bean("inMemCache") public Map<String, Integer> inMemoryCache() { return new
	 * HashMap<>(); }
	 */
}

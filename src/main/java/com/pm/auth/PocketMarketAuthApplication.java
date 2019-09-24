package com.pm.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication(scanBasePackages = {"com.pm"})
@EntityScan(basePackages = "com.pm.common.entities")
@EnableIntegration
public class PocketMarketAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocketMarketAuthApplication.class, args);
		/*
		Map<String, String> map = new HashMap<>();
		map.put("apiKey", "1233");
		map.put("message", "Tata");
		map.put("sender", "TXTLCL");
		map.put("numbers", "720432");
		String url = map.entrySet().stream().map(p -> p.getKey() + "=" + p.getValue()).reduce((p1, p2) -> p1 + "&" + p2).orElse("");
		System.out.println("Test1"+url);
		try {
			ctx.getBean(SmsGateway.class).sendSms(url);
		} catch(Exception e) {
			e.printStackTrace();
		}
		*/
	}
}

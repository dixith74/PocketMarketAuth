package com.pm.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.pm.auth.login.service.SmsGateway;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PocketMarketAuthApplicationTests {

	@Autowired
	SmsGateway smsGateway;

	@Test
	public void contextLoads() {

		Map<String, String> map = new HashMap<>();
		map.put("apikey", "1233");
		map.put("message", "Tata");
		map.put("sender", "TXTLCL");
		map.put("numbers", "720432");
		
		System.out.println("Test1");
		try {
			//smsGateway.sendSms(map);
//			Executor executor = Executors.newFixedThreadPool(5);
//			CompletableFuture.supplyAsync(() -> , executor).thenApply(s -> s)
//					.exceptionally(exception -> {
//						HttpClientErrorException ex = (HttpClientErrorException) exception.getCause();
//						return ex.getMessage();
//					}).thenAccept(s -> System.out.println(s));
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		System.out.println("Test2");
	}

}

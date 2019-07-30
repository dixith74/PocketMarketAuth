package com.pm.auth.util;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class InMemoryStore {

	private LoadingCache<String, Integer> otpCache;

	public InMemoryStore() {
		super();
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	public int generateOTP(String key) {
		/*
		 * Random random = new Random(); int otp = 100000 + random.nextInt(900000);
		 */
		int otp = OtpGenerator.generateOtp(4);
		System.out.println(otp);
		otpCache.put(key, otp);
		return otp;
	}

	public int getOtp(String key) {
		try {
			return otpCache.get(key);
		} catch (Exception e) {
			return 0;
		}
	}

	// This method is used to clear the OTP catched already
	public void clearOTP(String key) {
		otpCache.invalidate(key);
	}
}

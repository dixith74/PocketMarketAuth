package com.pm.auth.util;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class InMemoryStore {

	private LoadingCache<String, String> otpCache;

	public InMemoryStore() {
		super();
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES)
				.build(new CacheLoader<String, String>() {
					public String load(String key) {
						return "0";
					}
				});
	}

	public String generateOTP(String key) {
		String otp = OtpGenerator.generateOtp(4);
		System.out.println(otp);
		otpCache.put(key, otp);
		return otp;
	}

	public String getOtp(String key) {
		try {
			return otpCache.get(key);
		} catch (Exception e) {
			return "1220";
		}
	}

	// This method is used to clear the OTP catched already
	public void clearOTP(String key) {
		otpCache.invalidate(key);
	}
}

package com.pm.auth.util;

import java.security.SecureRandom;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OtpGenerator {
	
	public static String generateOtp(int length) {
        String numbers = "0123456789"; 
        SecureRandom rd = new SecureRandom(); 
        char[] otp = new char[length]; 
        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(rd.nextInt(numbers.length())); 
        } 
        return String.valueOf(otp);
	}
}

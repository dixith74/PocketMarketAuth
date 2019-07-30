package com.pm.auth.util;

import java.security.SecureRandom;

public class OtpGenerator {
	
	private OtpGenerator() {
		
	}

	public static Integer generateOtp(int length) {
        String numbers = "0123456789"; 
        SecureRandom rd = new SecureRandom(); 
        char[] otp = new char[length]; 
        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(rd.nextInt(numbers.length())); 
        } 
        String opt = String.valueOf(otp);
        return  Integer.valueOf(opt);
	}
}

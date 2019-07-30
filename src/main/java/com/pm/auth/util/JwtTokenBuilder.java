package com.pm.auth.util;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.pm.common.beans.UserWrapper;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenBuilder {
	
	private JwtTokenBuilder() {
		
	}

	public static String createJWT(String subject, UserWrapper user){
		
		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;

		Instant now = Instant.now();
		
		//We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("pmsecret");
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		//Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder()
				.setId(user.getUserId()+"")
				.setIssuedAt(Date.from(now))
				.setSubject(subject)
				.setIssuer(user.getMobileNo())
				.claim("mobile", user.getMobileNo())
				.claim("client", "mobile")
				.claim("scopes", Arrays.asList("ROLE_SELLER", "ROLE_BUYER"))
//				.setExpiration(Date.from(now.plus(5, ChronoUnit.HOURS)))
				.signWith(signatureAlgorithm, signingKey);
		return builder.compact();
	}
}

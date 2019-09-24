package com.pm.auth.util;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.pm.common.beans.JwtUser;
import com.pm.common.beans.UserWrapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenBuilder {
	
	private JwtTokenBuilder() {
		
	}

	public static String createJWT(UserWrapper user){
		
		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		Instant now = Instant.now();
		
		//We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("pocketmarket");
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		//Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder()
				.setId(UUID.randomUUID().toString())
				.setIssuedAt(Date.from(now))
				.setSubject(String.valueOf(user.getUserId()))
				.setIssuer("contact@pm.com")
				.claim("mobile_number", user.getMobileNo())
				.claim("full_name", user.getFullName())
				.claim("email", user.getEmail())
				.claim("user_role", Arrays.asList("ROLE_BUYER", "ROLE_SELLER"))
				//.claim("user_type", user.getUserType())
				//.claim("client_type", user.getClientType())
//				.setExpiration(Date.from(now.plus(5, ChronoUnit.HOURS)))
				.signWith(signatureAlgorithm, signingKey);
		return builder.compact();
	}
	/*
	public static void main(String[] args) {
		UserWrapper user = new UserWrapper();
		user.setUserId(23232);
		user.setMobileNo("900980980");
		user.setFirstName("Tata");
		user.setLastName("Oleti");
		user.setClientType("MOBILE");
		user.setUserType("SELLER");
		String token = createJWT(user);
		System.out.println(token);
		
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("pocketmarket");
        try {
        	Claims claims = Jwts.parser()
                    .setSigningKey(apiKeySecretBytes)
                    .parseClaimsJws(token)
                    .getBody();
        	String userId = claims.getSubject();
            @SuppressWarnings("unchecked")
			List<String> scopes = (ArrayList<String>)claims.get("user_role");
            String mobileNumber = (String)claims.get("mobile_number");
            String clientType = (String)claims.get("client_type");
            String userName = (String)claims.get("user_name");
            JwtUser jwtUser = new JwtUser(userId, mobileNumber, clientType, userName);
            System.out.println(jwtUser.toString());
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(jwtUser, null,
            		scopes.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception ignore) {
            SecurityContextHolder.clearContext();
        }
	}
	*/
	
}

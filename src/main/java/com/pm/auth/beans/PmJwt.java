package com.pm.auth.beans;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
@Getter
public class PmJwt {

	private String tokenType;
	private String accessToken;
	private String refreshToken;
	private String jti;
	private Long userId;
	private String userType;
}

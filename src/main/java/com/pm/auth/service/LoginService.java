package com.pm.auth.service;

import java.util.Map;

import com.pm.auth.beans.ActRequest;
import com.pm.auth.beans.JwtToken;
import com.pm.auth.beans.VerificationRequest;

public interface LoginService {

	public Map<String, String> register(ActRequest userRequest);
	public JwtToken login(VerificationRequest userRequest);
}

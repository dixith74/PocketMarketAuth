package com.pm.auth.login.service;

import java.util.Map;

import com.pm.auth.beans.ActRequest;
import com.pm.auth.beans.PmJwt;
import com.pm.auth.beans.VerificationRequest;

public interface LoginService {

	public Map<String, String> register(ActRequest userRequest);
	public PmJwt login(VerificationRequest userRequest);
}

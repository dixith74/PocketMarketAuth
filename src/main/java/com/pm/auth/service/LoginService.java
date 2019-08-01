package com.pm.auth.service;

import com.pm.auth.beans.ActRequest;
import com.pm.auth.beans.VerificationRequest;

public interface LoginService {

	public String register(ActRequest userRequest);
	public String login(VerificationRequest userRequest);
}

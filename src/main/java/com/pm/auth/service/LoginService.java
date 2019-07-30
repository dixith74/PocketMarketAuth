package com.pm.auth.service;

import com.pm.auth.beans.ActRequest;
import com.pm.auth.beans.VerificationRequest;
import com.pm.common.beans.UserWrapper;

public interface LoginService {

	public String register(ActRequest userRequest);
	public String login(VerificationRequest userRequest);
	public void update(UserWrapper user);
}

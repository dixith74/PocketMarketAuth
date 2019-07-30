package com.pm.auth.beans;

import org.json.JSONObject;

public class VerificationRequest extends UserData{

	boolean isProfileRequired = false;
	private JSONObject verification;

	public boolean isProfileRequired() {
		return isProfileRequired;
	}

	public void setProfileRequired(boolean isProfileRequired) {
		this.isProfileRequired = isProfileRequired;
	}

	public JSONObject getVerification() {
		return verification;
	}

	public void setVerification(JSONObject verification) {
		this.verification = verification;
	}
}

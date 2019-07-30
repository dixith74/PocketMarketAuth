package com.pm.auth.beans;

import org.json.JSONObject;

public class ActRequest extends UserData{

	boolean isProfileRequired = false;
	private JSONObject activation;

	public boolean isProfileRequired() {
		return isProfileRequired;
	}

	public void setProfileRequired(boolean isProfileRequired) {
		this.isProfileRequired = isProfileRequired;
	}

	public JSONObject getActivation() {
		return activation;
	}

	public void setActivation(JSONObject activation) {
		this.activation = activation;
	}
}

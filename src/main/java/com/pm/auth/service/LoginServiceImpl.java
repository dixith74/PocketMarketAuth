package com.pm.auth.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.auth.beans.ActRequest;
import com.pm.auth.beans.VerificationRequest;
import com.pm.auth.dao.LoginRepo;
import com.pm.auth.dao.UserRepository;
import com.pm.auth.util.InMemoryStore;
import com.pm.auth.util.JwtTokenBuilder;
import com.pm.auth.util.OtpGenerator;
import com.pm.common.beans.UserWrapper;
import com.pm.common.entities.PmUsers;
import com.pm.common.sms.SmsSender;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginRepo loginRepo;
	
	@Autowired
	public InMemoryStore inMemoryStore;
	
	@Override
	public String register(ActRequest actRequest) {
		UserWrapper uw = validate(actRequest, "register");
		if (uw == null) {
			uw = persistUser(actRequest);
		} 
		/*
		 * else { String mblNum = actRequest.getUserName(); uw =
		 * loginRepo.findBymobileNum(mblNum); }
		 */	
		// int otp = generateOtp();
		// System.out.println(otp);
		// sendToInMemStore(actRequest.getUserName(), otp);
		int otp = inMemoryStore.generateOTP(actRequest.getUserName());
		sendSms(otp);
		JSONObject response = prepareResponse(uw.getMobileNo());
		return response.toString();
	}

	@Override
	public String login(VerificationRequest userRequest) {
		
		UserWrapper user = null;
		JSONObject obj = new JSONObject();
		
		if (verifyOtp(userRequest, "otp_auth")) {
			String userData = null;
			user = loginRepo.findBymobileNum(userRequest.getUserName());
			try {
				userData = new ObjectMapper().writeValueAsString(user);
			} catch (JsonProcessingException e) {
			}
			String jwtToken = JwtTokenBuilder.createJWT("contact@pm.com", user);
			try {
				obj.put("description", new JSONObject().put("userIdentity", jwtToken).toString());
				obj.put("message", "Login Success");
			} catch (JSONException e) {}
		} else {
			try {
				obj.put("message", "Login failed");
			} catch (JSONException e) {}
		}
		return obj.toString();
	}

	private UserWrapper validate(ActRequest actReq, String type) {
		//boolean isValid = false;
		UserWrapper pmUser = null;
		if ("register".equalsIgnoreCase(type)) {
			try {
				String mode = actReq.getActivation().getString("mode");
				if ("otp".equalsIgnoreCase(mode)) {
					String mblNum = actReq.getUserName();
					pmUser = loginRepo.findBymobileNum(mblNum);
					/*
					 * if (pmUser != null) { isValid = true; }
					 */
				}
			} catch (JSONException e) {
			}
		}
		return pmUser;
	}
	
	private boolean verifyOtp(VerificationRequest verReq, String type) {
		boolean isValid = false;
		if ("otp_auth".equalsIgnoreCase(type)) {
			String mode = null;
			String code = null;
			try {
				JSONObject verification = verReq.getVerification();
				mode = verification.getString("mode");
				code = verification.getString("code");
				if ("otp".equalsIgnoreCase(mode) && code !=null) {
					// verify OTP in redis cache
					/*
					 * if (inMemStore.get(verReq.getUserName()) == Integer.parseInt(code)) {
					 * inMemStore.remove(verReq.getUserName()); isValid = true; }
					 */
					
					if (inMemoryStore.getOtp(verReq.getUserName()) == Integer.parseInt(code)) {
						inMemoryStore.clearOTP(verReq.getUserName());
						isValid = true;
					}
				}
			} catch (JSONException e) {
			}
		}
		return isValid;
	}
	
	private UserWrapper persistUser(ActRequest actReq) {
		PmUsers user = new PmUsers();
		user.setMobileNo(actReq.getUserName());
		user = loginRepo.save(user);
		return new UserWrapper(user.getUserId(), user.getFirstName(), user.getLastName(),
				user.getEmail(), user.getMobileNo(), user.getUserName(), user.getUserStts(), user.getRating(), 
				user.getCreatedTime(), user.getUpdatedTime(), user.getUserAddress(), user.getImage());
	}
	
	@Deprecated
	private int generateOtp() {
		return OtpGenerator.generateOtp(4);
	}
	
	@Deprecated
	private void sendToInMemStore(String mblNum, int otp) {
		//inMemStore.put(mblNum, otp);
	}
	
	private void sendSms(int otp) {
		// TWILIO SMS Gateway
		SmsSender.sendMessage(otp,"");
	}

	private JSONObject prepareResponse(String mblNum) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("description", new JSONObject().put("phone", mblNum).toString());
			obj.put("message", "Verification initiated");
		} catch (JSONException e) {
		}
		return obj;
	}
}

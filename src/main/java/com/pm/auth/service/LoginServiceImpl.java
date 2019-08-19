package com.pm.auth.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.auth.beans.ActRequest;
import com.pm.auth.beans.JwtToken;
import com.pm.auth.beans.VerificationRequest;
import com.pm.auth.dao.LoginRepo;
import com.pm.auth.util.InMemoryStore;
import com.pm.auth.util.JwtTokenBuilder;
import com.pm.auth.util.OtpGenerator;
import com.pm.common.beans.UserWrapper;
import com.pm.common.entities.PmUsers;
import com.pm.common.exception.BussinessExection;
import com.pm.common.sms.SmsSender;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepo loginRepo;

	@Autowired
	public InMemoryStore inMemoryStore;

	@Override
	public Map<String, String> register(ActRequest actRequest) {
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
		// sendSms(otp, uw.getMobileNo());
		return buildMessage(uw.getMobileNo());
	}

	@Override
	public JwtToken login(VerificationRequest userRequest) {

		if (verifyOtp(userRequest, "otp_auth")) {
			UserWrapper user = null;
			user = loginRepo.findBymobileNum(userRequest.getUserName());
			String jwtToken = JwtTokenBuilder.createJWT("contact@pm.com", user);
			return JwtToken.builder()
						.type("Bearer")
						.jti(UUID.randomUUID().toString())
						.token(jwtToken)
						.refreshToken("12345")
						.build();
		} else {
			throw new BussinessExection("Verification failed", 403);
		}
	}

	private UserWrapper validate(ActRequest actReq, String type) {
		// boolean isValid = false;
		UserWrapper pmUser = null;
		if ("register".equalsIgnoreCase(type)) {
			String mode = actReq.getMode();
			if ("otp".equalsIgnoreCase(mode)) {
				String mblNum = actReq.getUserName();
				pmUser = loginRepo.findBymobileNum(mblNum);
				/*
				 * if (pmUser != null) { isValid = true; }
				 */
			}
		}
		return pmUser;
	}

	private boolean verifyOtp(VerificationRequest verReq, String type) {
		boolean isValid = false;
		if ("otp_auth".equalsIgnoreCase(type)) {
			String mode = null;
			String code = null;
			mode = verReq.getMode();
			code = verReq.getCode();
			if ("otp".equalsIgnoreCase(mode) && code != null) {
				// verify OTP in redis cache
				/*
				 * if (inMemStore.get(verReq.getUserName()) == Integer.parseInt(code)) {
				 * inMemStore.remove(verReq.getUserName()); isValid = true; }
				 */
				/*
				 * if (inMemoryStore.getOtp(verReq.getUserName()) == Integer.parseInt(code)) {
				 * inMemoryStore.clearOTP(verReq.getUserName()); isValid = true; }
				 */
				isValid = true;
			}
		}
		return isValid;
	}

	private UserWrapper persistUser(ActRequest actReq) {
		PmUsers user = new PmUsers();
		user.setMobileNo(actReq.getUserName());
		user = loginRepo.save(user);
		return new UserWrapper(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getMobileNo(), user.getUserName(), user.getUserStts(), user.getRating(), user.getCreatedTime(),
				user.getUpdatedTime(), user.getUserAddress(), user.getImage());
	}

	@Deprecated
	private int generateOtp() {
		return OtpGenerator.generateOtp(4);
	}

	@Deprecated
	private void sendToInMemStore(String mblNum, int otp) {
		// inMemStore.put(mblNum, otp);
	}

	private void sendSms(int otp, String mbl) {
		// TWILIO SMS Gateway
		SmsSender.sendMessage(otp, mbl);
	}

	private Map<String, String> buildMessage(String mblNum) {
		Map<String, String> map = new HashMap<>();
		map.put("mobile", mblNum);
		map.put("message", "Verification initiated");
		return map;
	}
}

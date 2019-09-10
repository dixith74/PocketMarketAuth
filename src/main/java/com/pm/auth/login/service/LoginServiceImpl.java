package com.pm.auth.login.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.auth.beans.ActRequest;
import com.pm.auth.beans.PmJwt;
import com.pm.auth.beans.VerificationRequest;
import com.pm.auth.repo.UserRepository;
import com.pm.auth.util.InMemoryStore;
import com.pm.auth.util.JwtTokenBuilder;
import com.pm.common.beans.ClientType;
import com.pm.common.beans.UserWrapper;
import com.pm.common.entities.PmUsers;
import com.pm.common.exception.BussinessExection;
import com.pm.common.sms.SmsSender;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	public InMemoryStore inMemoryStore;
	
	@Autowired
	private SmsSender smsSender;

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
		String otp = inMemoryStore.generateOTP(actRequest.getUserName());
		sendSms(otp, uw.getMobileNo());
		return buildMessage(uw.getMobileNo());
	}

	@Override
	public PmJwt login(VerificationRequest userRequest) {

		if (verifyOtp(userRequest)) {
			UserWrapper user = null;
			user = userRepo.findBymobileNum(userRequest.getUserName());
			String jwtToken = JwtTokenBuilder.createJWT(user);
			return PmJwt.builder()
						.tokenType("Bearer")
						.jti(UUID.randomUUID().toString())
						.accessToken(jwtToken)
						.refreshToken(UUID.randomUUID().toString())
						.userId(user.getUserId())
						.userType(user.getUserType())
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
				pmUser = userRepo.findBymobileNum(mblNum);
				/*
				 * if (pmUser != null) { isValid = true; }
				 */
			}
		}
		return pmUser;
	}

	private boolean verifyOtp(VerificationRequest verReq) {
		boolean isValid = false;
		String mode = verReq.getMode();;
		String code = verReq.getCode();;
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
		return isValid;
	}

	private UserWrapper persistUser(ActRequest actReq) {
		PmUsers user = new PmUsers();
		user.setMobileNo(actReq.getUserName());
		user.setCreatedTime(new Date());
		user.setUpdatedTime(new Date());
		user.setClientType(ClientType.fromShortName(actReq.getClientType()));
		user = userRepo.save(user);
		return new UserWrapper(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getMobileNo(), user.getUserName(), user.getUserStts(), user.getRating(), user.getCreatedTime(),
				user.getUpdatedTime(), user.getUserAddress(), user.getImage(), user.getUserType(), user.getClientType().name());
	}

	private void sendSms(String otp, String mbl) {
		smsSender.sendMessage(prepareSmsMessage(otp), mbl);
	}

	private String prepareSmsMessage(String otp) {
		return otp + " is OTP for login at Pocket Market.OTPs are SECRET.DO NOT disclose to anyone.";
	}

	private Map<String, String> buildMessage(String mblNum) {
		Map<String, String> map = new HashMap<>();
		map.put("mobile", mblNum);
		map.put("message", "Verification initiated");
		return map;
	}
}

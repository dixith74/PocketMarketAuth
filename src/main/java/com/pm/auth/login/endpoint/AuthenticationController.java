package com.pm.auth.login.endpoint;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pm.auth.beans.ActRequest;
import com.pm.auth.beans.PmJwt;
import com.pm.auth.beans.VerificationRequest;
import com.pm.auth.login.service.LoginService;

@RestController
@RequestMapping("user")
public class AuthenticationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private LoginService loginService;

	@PostMapping("/register")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Map<String, String>> registrationHandler(@RequestBody() ActRequest userRequest) {
		LOGGER.info("Registration request {}", userRequest);
		Map<String, String> response = loginService.register(userRequest);
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/login")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<PmJwt> loginHandler(@RequestBody() VerificationRequest userRequest) {
		LOGGER.info("OTP auth request {}", userRequest);
		PmJwt jwt = loginService.login(userRequest);
		return ResponseEntity.ok().body(jwt);
	}
	
	@GetMapping("/ping")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<String> loginHandler() {
		return ResponseEntity.ok().body("Ok");
	}
}

package com.pm.auth.login;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pm.auth.beans.ActRequest;
import com.pm.auth.beans.VerificationRequest;
import com.pm.auth.service.LoginService;
import com.pm.common.beans.UserWrapper;

@RestController
@RequestMapping("user")
public class AuthController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private LoginService loginService;

	@PostMapping("/register")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<String> registrationHandler(@RequestBody() ActRequest userRequest) {
		LOGGER.info("Registration request {}", userRequest);
		String response = loginService.register(userRequest);
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/login")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<String> loginHandler(@RequestBody() VerificationRequest userRequest) {
		LOGGER.info("OTP auth request {}", userRequest);
		String jwt = loginService.login(userRequest);
		return ResponseEntity.ok().body(jwt);
	}
	
	@PutMapping("/update/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<String> updateUser(@RequestBody() UserWrapper user, @PathParam("id") Long id) {
		LOGGER.info("Updating user {}", user);
		user.setUserId(id);
		loginService.update(user);
		return ResponseEntity.ok().build();
	}
}

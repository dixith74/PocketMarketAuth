package com.pm.auth.user.endpoint;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pm.auth.user.service.UserService;
import com.pm.common.beans.Address;
import com.pm.common.beans.UserAddressWrapper;
import com.pm.common.beans.UserWrapper;

import brave.propagation.TraceContext;

@RestController
@RequestMapping("users")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PutMapping("/{userid}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Void> updateUser(@PathVariable("userid") Long userid, @RequestBody() UserWrapper user) {
		LOGGER.info("Updating user {}", user);
		user.setUserId(userid);
		userService.update(user);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/profile/upload")
    public ResponseEntity<Void> handleFileUpload(@RequestParam("file") MultipartFile file, 
    		@RequestParam("userId") Long userId) {
		userService.store(file, userId);
        //URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand("122").toUri();
        //ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path("123").build().toUri());
        return ResponseEntity.ok().build();
    }
	
	@GetMapping("/{userid}")
	@ResponseStatus(code = HttpStatus.OK)
	public UserWrapper getUser(@PathVariable("userid") Long userid, HttpServletRequest httpServletRequest) {
		TraceContext context = (TraceContext) httpServletRequest.getAttribute(TraceContext.class.getName());
		LOGGER.info("Updating user {} {} {}", userid, context.traceIdString(), httpServletRequest.getHeader("Authorization"));
		return userService.getUser(userid);
	}
	
	@PostMapping("/address")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Void> addDeliveryAddress(@RequestBody() Address adress) {
		userService.addAddress(adress);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{userid}/address")
	@ResponseStatus(code = HttpStatus.OK)
	public UserAddressWrapper getUserWithAddress(@PathVariable("userid") Long userid) {
		//System.out.println(SecurityContextHolder.getContext().getAuthentication().toString());
		//System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		//JwtUser usr = (JwtUser)SecurityContextHolder.getContext().getAuthentication().getDetails();
		LOGGER.info("getUserWithAddress {}", userid);
		return userService.getUserAddress(userid);
	}
}

package com.pm.auth.user.endpoint;

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
import com.pm.common.beans.UserWrapper;

@RestController
@RequestMapping("user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PutMapping("/update/{userid}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<String> updateUser(@PathVariable("userid") Long userid, @RequestBody() UserWrapper user) {
		LOGGER.info("Updating user {}", user);
		user.setUserId(userid);
		userService.update(user);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file, 
    		@RequestParam("userId") Long userId) {
		userService.store(file, userId);
        //URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand("122").toUri();
        //ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path("123").build().toUri());
        return ResponseEntity.ok().build();
    }
	
	@GetMapping("/{userid}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<UserWrapper> getUser(@PathVariable("userid") Long userid) {
		LOGGER.info("Updating user {}", userid);
		return ResponseEntity.ok(userService.getUser(userid));
	}
}

package com.pm.auth.login;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pm.auth.service.UserService;
import com.pm.common.beans.UserWrapper;

@RestController
@RequestMapping("user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PutMapping("/update/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<String> updateUser(@RequestBody() UserWrapper user, @PathParam("id") Long id) {
		LOGGER.info("Updating user {}", user);
		user.setUserId(id);
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
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<String> getUser(@PathParam("id") Long id) {
		LOGGER.info("Updating user {}", id);
		userService.getUser(id);
		return ResponseEntity.ok().build();
	}
}

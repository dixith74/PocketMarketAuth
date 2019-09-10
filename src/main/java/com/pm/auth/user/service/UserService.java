package com.pm.auth.user.service;

import org.springframework.web.multipart.MultipartFile;

import com.pm.common.beans.UserWrapper;

public interface UserService {
	public void update(UserWrapper user);
	public void store(MultipartFile file, Long userId);
	public UserWrapper getUser(Long id);
}

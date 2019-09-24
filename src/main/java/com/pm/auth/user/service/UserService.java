package com.pm.auth.user.service;

import org.springframework.web.multipart.MultipartFile;

import com.pm.common.beans.Address;
import com.pm.common.beans.UserAddressWrapper;
import com.pm.common.beans.UserWrapper;

public interface UserService {
	public void update(UserWrapper user);
	public void store(MultipartFile file, Long userId);
	public UserWrapper getUser(Long id);
	void addAddress(Address adress);
	public UserAddressWrapper getUserAddress(Long id);
}

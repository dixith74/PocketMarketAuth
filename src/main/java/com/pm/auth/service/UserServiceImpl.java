package com.pm.auth.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.pm.auth.aws.AmazonClientService;
import com.pm.auth.dao.UserRepository;
import com.pm.common.beans.UserWrapper;
import com.pm.common.entities.PmUsers;
import com.pm.common.exception.BussinessExection;
import com.pm.common.utility.Utility;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public AmazonClientService amazonClientService;

	@Override
	public void update(UserWrapper user) {
		PmUsers pmUser = userRepository.findById(user.getUserId()).orElseThrow(() -> new BussinessExection("User not found"));
		pmUser.setFirstName(user.getFirstName());
		pmUser.setLastName(user.getLastName());
		pmUser.setEmail(user.getEmail());
		userRepository.save(pmUser);
	}

	@Override
	public void store(MultipartFile multipartFile, Long userId) {
		PmUsers pmUser = userRepository.findById(userId).orElseThrow(() -> new BussinessExection("User not found"));
		File file = Utility.convertMultiPartToFile(multipartFile);
	    String fileName = Utility.generateFileName(multipartFile);
		amazonClientService.getS3Client().putObject(new PutObjectRequest("prodcuts",fileName, file)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		String profilePic = amazonClientService.getS3Client().getUrl("prodcuts",fileName).toString();
		pmUser.setImage(profilePic);
		userRepository.save(pmUser);
	}

	@Override
	public UserWrapper getUser(Long id) {
		return userRepository.findByUserId(id);
	}
}

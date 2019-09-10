package com.pm.auth.user.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.pm.auth.repo.UserRepository;
import com.pm.common.aws.AmazonClientService;
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
	
	private static final String APP_NAME = "pocket-market";

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
	    fileName = "assets/users/" + userId + "/" + fileName;
	    AmazonS3 amazonS3 = amazonClientService.getS3Client();
	    if (amazonS3.doesBucketExistV2(APP_NAME)) {
	    	amazonS3.putObject(new PutObjectRequest(APP_NAME,fileName, file)
	    			.withCannedAcl(CannedAccessControlList.PublicRead));
	    	String uploadedFileUrl = amazonS3.getUrl(APP_NAME,fileName).toString();
	    	pmUser.setImage(uploadedFileUrl);
	    	userRepository.save(pmUser);
	    }
	}

	@Override
	public UserWrapper getUser(Long id) {
		return userRepository.findByUserId(id);
	}
}

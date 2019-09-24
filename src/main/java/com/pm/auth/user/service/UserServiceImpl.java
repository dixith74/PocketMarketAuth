package com.pm.auth.user.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.pm.auth.repo.AddressRepository;
import com.pm.auth.repo.UserRepository;
import com.pm.common.aws.AmazonClientService;
import com.pm.common.beans.Address;
import com.pm.common.beans.ClientType;
import com.pm.common.beans.UserAddressWrapper;
import com.pm.common.beans.UserWrapper;
import com.pm.common.entities.PmAddress;
import com.pm.common.entities.PmUsers;
import com.pm.common.exception.BussinessExection;
import com.pm.common.utility.Utility;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public AmazonClientService amazonClientService;

	@Autowired
	private AddressRepository addressRepository;

	private static final String APP_NAME = "pocket-market";

	@Override
	public void update(UserWrapper user) {
		PmUsers pmUser = userRepository.findById(user.getUserId()).orElseThrow(() -> new BussinessExection("User not found", 404));
		pmUser.setFirstName(user.getFirstName());
		pmUser.setLastName(user.getLastName());
		pmUser.setEmail(user.getEmail());
		pmUser.setFullName(user.getFullName());
		pmUser.setUpdatedTime(new Date());
		pmUser.setClientType(ClientType.fromShortName("MOBILE"));
		userRepository.save(pmUser);
	}

	@Override
	public void store(MultipartFile multipartFile, Long userId) {
		PmUsers pmUser = userRepository.findById(userId).orElseThrow(() -> new BussinessExection("User not found", 404));
		File file = Utility.convertMultiPartToFile(multipartFile);
		String fileName = Utility.generateFileName(multipartFile);
		fileName = "assets/users/" + userId + "/" + fileName;
		AmazonS3 amazonS3 = amazonClientService.getS3Client();
		if (amazonS3.doesBucketExistV2(APP_NAME)) {
			amazonS3.putObject(
					new PutObjectRequest(APP_NAME, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
			String uploadedFileUrl = amazonS3.getUrl(APP_NAME, fileName).toString();
			pmUser.setImage(uploadedFileUrl);
			userRepository.save(pmUser);
		}
	}

	@Override
	public UserWrapper getUser(Long id) {
		UserWrapper usr = userRepository.findByUserId(id);
		if (usr == null) {
			throw new BussinessExection("User not found", 404);
		}
		return userRepository.findByUserId(id);
	}

	@Override
	public void addAddress(Address address) {
		PmAddress pmAddress = PmAddress.builder()
				.pmUsers(userRepository.findById(address.getUserId())
						.orElseThrow(() -> new BussinessExection("User not found")))
				.firstName(address.getFirstName()).lastName(address.getLastName()).city(address.getCity())
				.state(address.getState()).street(address.getStreet()).country(address.getCountry())
				.addressOne(address.getAddressOne()).addressTwo(address.getAddressTwo()).pincode(address.getPincode())
				.addressType(address.getAddressType()).build();
		addressRepository.save(pmAddress);
	}

	@Override
	public UserAddressWrapper getUserAddress(Long userId) {
		PmUsers user = userRepository.findById(userId).orElseThrow(() -> new BussinessExection("User not found", 404));
		UserAddressWrapper usrWrpr = UserAddressWrapper.builder().userId(user.getUserId()).userName(user.getUserName())
				.firstName(user.getFirstName()).lastName(user.getLastName()).fullName(user.getFullName())
				.mobileNo(user.getMobileNo()).email(user.getEmail()).image(user.getImage()).rating(user.getRating())
				.userStts(user.getUserStts()).build();
		List<Address> addrList = user.getPmUserAddress().stream()
				.map(addr -> Address.builder().addressId(addr.getAddressId()).userId(user.getUserId())
						.firstName(addr.getFirstName()).lastName(addr.getLastName()).addressOne(addr.getAddressOne())
						.addressTwo(addr.getAddressTwo()).addressType(addr.getAddressType()).state(addr.getState())
						.street(addr.getStreet()).city(addr.getCity()).country(addr.getCountry())
						.pincode(addr.getPincode()).build())
				.collect(Collectors.toList());
		usrWrpr.setAddresses(addrList);
		return usrWrpr;
	}
}

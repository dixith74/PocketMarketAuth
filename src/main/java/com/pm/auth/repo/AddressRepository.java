package com.pm.auth.repo;

import org.springframework.data.repository.CrudRepository;

import com.pm.common.entities.PmAddress;

public interface AddressRepository extends CrudRepository<PmAddress, Long> {
	
	public PmAddress findByPmUsers_UserIdAndAddressType(Long userId, String addressType);
}

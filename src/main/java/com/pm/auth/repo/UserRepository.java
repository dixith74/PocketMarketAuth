package com.pm.auth.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pm.common.beans.UserWrapper;
import com.pm.common.entities.PmUsers;

public interface UserRepository extends CrudRepository<PmUsers, Long> {

	@Query(value = "select new com.pm.common.beans.UserWrapper(u.userId, u.firstName, u.lastName, u.email, "
			+ "u.mobileNo, u.userName, u.userStts, u.rating, u.image, u.userAddress, u.userType) from PmUsers u where u.userId = :id")
	public UserWrapper findByUserId(@Param("id") long id);

	// @Query(value = "FROM PmUsers u where u.mobileNo = :mbl")
	public PmUsers findBymobileNo(String mblNum);

	@Query(value = "select new com.pm.common.beans.UserWrapper(u.userId, u.firstName, u.lastName, u.email, "
			+ "u.mobileNo, u.userName, u.userStts, u.rating, u.image, u.userAddress, u.userType) from PmUsers u where u.mobileNo = :mbl")
	public UserWrapper findBymobileNum(@Param("mbl") String mblNum);
}

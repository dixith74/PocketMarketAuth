package com.pm.auth.dao;

import org.springframework.data.repository.CrudRepository;

import com.pm.common.entities.PmUsers;

public interface UserRepository extends CrudRepository<PmUsers, Long> {
}

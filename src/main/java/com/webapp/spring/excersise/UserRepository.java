package com.webapp.spring.excersise;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Query(value = "SELECT * FROM USERS u", nativeQuery = true)
	Collection<User> findAllActiveUsers();

	@Query(value = "SELECT * FROM USERS u WHERE u.username = ?1 ", nativeQuery = true)
	User findUserByUserName(String username);
}

package com.webapp.spring.excersise;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PermitRepositorty extends CrudRepository<Permit, Integer> {

	@Query(value = "SELECT * FROM PERMITS p WHERE p.director_id != null ", nativeQuery = true)
	Collection<Permit> findConfirmedPermits();

	@Query(value = "SELECT * FROM PERMITS p WHERE p.director_id = null OR p.director_id =0", nativeQuery = true)
	Collection<Permit> findRequestedPermits();
}

package com.webapp.spring.excersise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController indicates that any client will send a request with our given
 *                 endpoints(/employee/view_permits/rest,
 *                 /employee/view_confirmed_permits/rest) and we will get a
 *                 response json body along with status
 *
 */
@RestController
public class RestPermitController {

	@Autowired
	PermitRepositorty permitRepositorty;

	@Autowired
	UserRepository userRepositorty;

	@GetMapping("/employee/view_permits/rest")
	public Iterable<Permit> showAll() {
		return permitRepositorty.findAll();
	}

	@GetMapping("/employee/view_confirmed_permits/rest")
	public Iterable<Permit> showConfirmedPermits() {
		return permitRepositorty.findConfirmedPermits();
	}

	@GetMapping("/employee/view_requested_permits/rest")
	public Iterable<Permit> showRequestedPermits() {
		return permitRepositorty.findRequestedPermits();
	}
}

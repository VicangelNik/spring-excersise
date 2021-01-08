package com.webapp.spring.excersise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestPermitController {

	@Autowired
	PermitRepositorty permitRepositorty;

	@Autowired
	UserRepository userRepositorty;

	@GetMapping("/employee/view_permits/rest")
	public String showAll(Model model) {
		model.addAttribute("permit", permitRepositorty.findAll());
		return "table_permits"; // returns the template
	}

}

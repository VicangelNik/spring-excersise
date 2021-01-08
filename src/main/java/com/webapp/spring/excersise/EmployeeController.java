package com.webapp.spring.excersise;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {

	@Autowired
	UserRepository userRepositorty;

	@RequestMapping("/employee")
	public String showEmployeeOptions() {
		return "employee_options"; // returns the template
	}

	@GetMapping("/employee/show_my_details")
	public String showEmployeeDetails(HttpServletRequest request, Model model) {
		User user = userRepositorty.findUserByUserName(request.getRemoteUser());
		model.addAttribute("user", user);
		return "show_my_details";
	}

}

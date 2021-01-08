package com.webapp.spring.excersise;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PermitController {

	@Autowired
	PermitRepositorty permitRepositorty;

	@Autowired
	UserRepository userRepositorty;

	@GetMapping("/employee/view_permits")
	public String showAll(Model model) {
		model.addAttribute("permit", permitRepositorty.findAll());
		return "table_permits"; // returns the template
	}

	@GetMapping("/employee/view_confirmed_permits")
	public String showConfirmedPermits(Model model) {
		model.addAttribute("permit", permitRepositorty.findConfirmedPermits());
		return "table_permits"; // returns the template
	}

	@GetMapping("/employee/view_requested_permits")
	public String showRequestedPermits(Model model) {
		model.addAttribute("permit", permitRepositorty.findRequestedPermits());
		model.addAttribute("viewRequestedPermits", true);
		return "table_permits"; // returns the template
	}

	@GetMapping("/employee/register_permit")
	public String showRegistrationForm(Model model) {
		Permit permit = new Permit();
		model.addAttribute("permit", permit);
		return "register_permit"; // returns the template
	}

	@PostMapping("/employee/save_permit")
	public String saveRole(@ModelAttribute("permit") Permit permit, Model model, HttpServletRequest request) {
		Optional<Permit> permitOptional = permitRepositorty.findById(permit.getPermitId());
		if (permitOptional.isPresent()) {
			User user = userRepositorty.findUserByUserName(request.getRemoteUser());
			Permit permitToUpdate = permitOptional.get();
			permitToUpdate.setConfirmationDate(permit.getConfirmationDate());
			permitToUpdate.setStatus(permit.getStatus());
			permitToUpdate.setDirectorId(user.getUserId());
			permitRepositorty.save(permitToUpdate);
			model.addAttribute("permit", permitRepositorty.findRequestedPermits());
			model.addAttribute("viewRequestedPermits", true);
			return "table_permits";
		} else {
			permitRepositorty.save(permit);
			model.addAttribute("permit", permit);
			return "display_success_permit";
		}
	}

	@RequestMapping(path = { "/employee/confirm_permit", "/employee/confirm_permit/{id}" })
	public String editPermitById(Model model, @PathVariable("id") Integer id) {
		if (id != null) {
			Optional<Permit> entity = permitRepositorty.findById(id);
			model.addAttribute("permit", entity);
		} else {
			model.addAttribute("permit", new Permit());
		}
		return "confirm_permit";
	}
}

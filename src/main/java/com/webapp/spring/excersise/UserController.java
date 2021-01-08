package com.webapp.spring.excersise;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepositorty;

	@Autowired
	RoleRepositorty RoleRepositorty;

	@GetMapping("/console/register_user")
	public String showRegistrationForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("roles", RoleRepositorty.findAll());
		return "register_user"; // returns the template
	}

	@PostMapping("/console/save_user")
	public String saveEmployee(@ModelAttribute("user") User user, Model model) {
		User userToUpdate = (User) model.getAttribute("user");
		if (userToUpdate.getUserId() != 0) {
			userToUpdate.setAge(user.getAge());
			userToUpdate.setAddress(user.getAddress());
			userToUpdate.setDept(user.getDept());
			userToUpdate.setEmail(user.getEmail());
			userToUpdate.setJoiningDate(user.getJoiningDate());
			userToUpdate.setName(user.getName());
			userToUpdate.setPassword(user.getPassword());
			userToUpdate.setTelephone(user.getTelephone());
			userToUpdate.setUsername(user.getPassword());
			userToUpdate.setRoles(user.getRoles());
			model.addAttribute("user", userToUpdate);
			userRepositorty.save(userToUpdate);
		} else {
			model.addAttribute("user", user);
			userRepositorty.save(user);
		}
		return "display_form"; // returns the template
	}

	@GetMapping("/console/show_all")
	public String showAll(Model model) {
		model.addAttribute("user", userRepositorty.findAll());
		return "table_users"; // returns the template
	}

	@RequestMapping(path = { "/console/edit", "/console/edit/{id}" })
	public String editUserById(Model model, @PathVariable("id") Integer id) {
		if (id != null) {
			Optional<User> entity = userRepositorty.findById(id);
			model.addAttribute("user", entity);
			model.addAttribute("roles", RoleRepositorty.findAll());
		} else {
			model.addAttribute("user", new User());
		}
		return "register_user";
	}

	@RequestMapping(path = "/console/delete/{id}")
	public String deleteEmployeeById(Model model, @PathVariable("id") Integer id) {
		userRepositorty.deleteById(id);
		model.addAttribute("user", userRepositorty.findAll());
		return "table_users";
	}
}

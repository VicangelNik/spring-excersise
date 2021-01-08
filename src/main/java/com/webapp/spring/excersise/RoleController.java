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
public class RoleController {

	@Autowired
	RoleRepositorty roleRepositorty;

	@Autowired
	AuthorityRepositorty authorityRepositorty;

	@GetMapping("/console/register_role")
	public String showRegistrationForm(Model model) {
		Role role = new Role();
		model.addAttribute("authorities", authorityRepositorty.findAll());
		model.addAttribute("role", role);
		return "register_role"; // returns the template
	}

	@PostMapping("/console/save_role")
	public String saveRole(@ModelAttribute("role") Role role, Model model) {
		roleRepositorty.save(role);
		model.addAttribute("role", roleRepositorty.findAll());
		return "table_roles"; // returns the template
	}

	@GetMapping("/console/show_roles")
	public String showAll(Model model) {
		model.addAttribute("role", roleRepositorty.findAll());
		return "table_roles"; // returns the template
	}

	@RequestMapping(path = { "/console/edit_role", "/console/edit_role/{id}" })
	public String editRoleById(Model model, @PathVariable("id") Integer id) {
		if (id != null) {
			Optional<Role> entity = roleRepositorty.findById(id);
			model.addAttribute("role", entity);
			model.addAttribute("authorities", authorityRepositorty.findAll());
		} else {
			model.addAttribute("role", new Role());
		}
		return "register_role";
	}

	@RequestMapping(path = "/console/delete_role/{id}")
	public String deleteRoleById(Model model, @PathVariable("id") Integer id) {
		roleRepositorty.deleteById(id);
		model.addAttribute("role", roleRepositorty.findAll());
		return "table_roles"; // returns the template
	}
}

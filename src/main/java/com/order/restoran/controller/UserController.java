package com.order.restoran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.order.restoran.entity.User;
import com.order.restoran.service.UserServiceImpl;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserServiceImpl service;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/admin/control";
	}
	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name = "id", required = false) Long id){
		if (id != null) {
			User entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new User());
		}
		return "user-add-edit";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(User entity) {
		if(entity.getId()==null)service.userRegistration(entity);
		else {
			User read = service.read(entity.getId());
			read.update(entity.getPassword(), entity.getFirstname(), entity.getLastname(), entity.getEmail());
			read.setPassword(passwordEncoder.encode(read.getPassword()));
			read.setUsername(entity.getUsername());
			service.update(read);
		}
		return "redirect:/admin/control";
	}
}

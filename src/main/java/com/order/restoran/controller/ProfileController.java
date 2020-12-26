package com.order.restoran.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.order.restoran.entity.User;
import com.order.restoran.service.UserServiceImpl;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	UserServiceImpl service;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	String getProfile(Model model, Principal principal) {
		model.addAttribute("entity", service.findByUsername(principal.getName()));
		return "profile";
	}

	@RequestMapping(path = "/delete")
	public String delete(Model model, Principal principal) throws Exception {
		service.delete(service.findByUsername(principal.getName()).getId());
		return "redirect:/logout";
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public String createOrder(User entity, Principal principal, Model model) {
		User user = service.findByUsername(principal.getName());
		user.update(entity.getPassword(), entity.getFirstname(), entity.getLastname(),
				entity.getEmail());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		model.addAttribute("success", service.update(user));
		model.addAttribute("entity", service.read(user.getId()));
		return "profile";
	}

}

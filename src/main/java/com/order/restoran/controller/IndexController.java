package com.order.restoran.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.order.restoran.entity.User;
import com.order.restoran.service.TypeTableService;
import com.order.restoran.service.UserServiceImpl;

@Controller
@RequestMapping(path = { "/", "/index" })
public class IndexController {
	@Autowired
	UserServiceImpl userService;

	@Autowired
	TypeTableService typeTableService;
	
	@GetMapping
	String getIndex(Model model, Principal principal) {
		if (principal == null)
			return "index";
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("entity", user);
		return "index";
	}
	
	@GetMapping
	@RequestMapping("/sheme")
	String getSheme(Model model, Principal principal) {
		model.addAttribute("types", typeTableService.repository.findAll());
		return "sheme";
	}
	
	@GetMapping
	@RequestMapping("/preorder")
	String getPreorder(Model model, Principal principal) {
		model.addAttribute("entity", userService.findByUsername(principal.getName()));
		return "preorder";
	}

}

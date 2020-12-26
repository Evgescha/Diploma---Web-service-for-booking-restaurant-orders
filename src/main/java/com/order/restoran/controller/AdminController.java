package com.order.restoran.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.order.restoran.entity.User;
import com.order.restoran.service.FoodService;
import com.order.restoran.service.OrderService;
import com.order.restoran.service.TypeFoodService;
import com.order.restoran.service.TypeTableService;
import com.order.restoran.service.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	TypeFoodService serviceTypeFood;
	
	@Autowired
	FoodService serviceFood;	

	@Autowired
	TypeTableService serviceTypeTable;

	@Autowired
	UserServiceImpl serviceUser;
	
	@GetMapping
	@RequestMapping("/control")
	String getIndex(Model model) {
		model.addAttribute("typesFood", serviceTypeFood.repository.findAll());
		model.addAttribute("foods",  serviceFood.repository.findAll());
		model.addAttribute("typesTable",  serviceTypeTable.repository.findAll());
		model.addAttribute("users",  serviceUser.repository.findAll());
		return "control";
	}
}

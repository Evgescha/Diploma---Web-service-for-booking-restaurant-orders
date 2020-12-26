package com.order.restoran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.order.restoran.service.FoodService;
import com.order.restoran.service.TypeFoodService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	FoodService serviceFood;

	@Autowired
	TypeFoodService serviceTypeFood;

	@GetMapping
	String getService(Model model) {
		model.addAttribute("foods", serviceFood.repository.findAll());
		model.addAttribute("types", serviceTypeFood.repository.findAll());

		return "menu";
	}
	
	@GetMapping
	@RequestMapping("/type/{id}")
	String getSelectService(Model model, @PathVariable("id") Long id) {
		model.addAttribute("foods", serviceFood.repository.findByTypeFood(serviceTypeFood.read(id)));
		model.addAttribute("types", serviceTypeFood.repository.findAll());

		return "menu";
	}
	
}

package com.order.restoran.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.order.restoran.entity.Food;
import com.order.restoran.entity.TypeFood;
import com.order.restoran.service.FoodService;
import com.order.restoran.service.TypeFoodService;

@Controller
@RequestMapping("/food")
public class FoodController {

	@Autowired
	FoodService service;

	@Autowired
	TypeFoodService typeFoodService;

	@GetMapping
	String getService(Model model) {
		List<Food> list = service.repository.findAll();

		if (list == null)
			model.addAttribute("list", null);
		else
			model.addAttribute("list", list);

		return "food-list";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/food";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name = "id", required = false) Long id, Principal principal){

		List<TypeFood> types = typeFoodService.repository.findAll();

		if (id != null) {
			Food entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new Food());
		}
		model.addAttribute("types", types);
		return "food-add-edit";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(Food entity, @Param("catId") Long catId) throws Exception {

		entity.setTypeFood(typeFoodService.read(catId));
		service.create(entity);
		return "redirect:/food";
	}
}

package com.order.restoran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.order.restoran.entity.Order;
import com.order.restoran.service.FoodService;
import com.order.restoran.service.OrderService;
import com.order.restoran.service.StatusService;
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
	@Autowired
	OrderService serviceOrder;
	@Autowired
	StatusService serviceStatus;
	
	@GetMapping
	@RequestMapping("/control")
	String getIndex(Model model) {
		model.addAttribute("typesFood", serviceTypeFood.repository.findAll());
		model.addAttribute("foods",  serviceFood.repository.findAll());
		model.addAttribute("typesTable",  serviceTypeTable.repository.findAll());
		model.addAttribute("users",  serviceUser.repository.findAll());
		return "control";
	}
	
	@GetMapping
	@RequestMapping("/booking")
	String getBooking(Model model) {
		model.addAttribute("list",  serviceOrder.repository.findAll());
		return "booking";
	}
	
	@GetMapping
	@RequestMapping("/confirmOrder/{id}")
	String confirmOrder(Model model,@PathVariable("id") Long id) {
		Order order = serviceOrder.read(id);
		order.setStatus(serviceStatus.read(3));
		serviceOrder.update(order);
		return "redirect:/admin/booking";
	}

	@GetMapping
	@RequestMapping("/completeOrder/{id}")
	String conpleteOrder(Model model,@PathVariable("id") Long id) {
		Order order = serviceOrder.read(id);
		order.setStatus(serviceStatus.read(4));
		serviceOrder.update(order);
		return "redirect:/admin/booking";
	}
	@GetMapping
	@RequestMapping("/deleteOrder/{id}")
	String deleteOrder(Model model,@PathVariable("id") Long id) throws Exception {
		serviceOrder.delete(id);
		return "redirect:/admin/booking";
	}
}

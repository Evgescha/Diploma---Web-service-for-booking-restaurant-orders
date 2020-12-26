package com.order.restoran.controller;

import java.security.Principal;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.order.restoran.entity.Order;
import com.order.restoran.entity.TypeTable;
import com.order.restoran.entity.User;
import com.order.restoran.service.FoodService;
import com.order.restoran.service.OrderService;
import com.order.restoran.service.StatusService;
import com.order.restoran.service.TypeTableService;
import com.order.restoran.service.UserServiceImpl;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	UserServiceImpl service;
	@Autowired
	StatusService serviceStatus;
	@Autowired
	FoodService serviceFood;
	@Autowired
	OrderService serviceOrder;
	@Autowired
	TypeTableService serviceTable;
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
	public String createOrder(User entity, Principal principal, RedirectAttributes ra) {
		User user = service.findByUsername(principal.getName());
		user.update(entity.getPassword(), entity.getFirstname(), entity.getLastname(),
				entity.getEmail());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		ra.addAttribute("success", service.update(user));
		return "redirect:/profile";
	}
	@RequestMapping(path = "/addFood/{id}", method = RequestMethod.GET)
	public String addFoodInOrder(Principal principal, @PathVariable("id") Long id) {
		User user = service.findByUsername(principal.getName());
		//проверка, есть ли не оформленный заказ
		boolean isHas=false;
		Order order=null;
		for(Order tmp:user.getMyOrders()) {
			if(tmp.getStatus().getId()==1) {isHas=true;order=tmp;break;}
		}
		// если заказа нет, перебросить на страницу бронирования
		if(!isHas) return "redirect:/sheme";
		
		order.getFoods().add(serviceFood.read(id));
		serviceOrder.update(order);
		return "redirect:/preorder";
	}
	//добавление новой брони по столику
	@RequestMapping(path = "/bookingAt/{id}", method = RequestMethod.GET)
	public String bookingTableAt(Principal principal, @PathVariable("id") Long id) {
		//считываем пользователя и стол
		User user = service.findByUsername(principal.getName());
		TypeTable table = serviceTable.read(id);
		//проверка, если ли заказы в процессе создания
		boolean isHas=false;
		Order order=null;
		for(Order tmp:user.getMyOrders()) {
			if(tmp.getStatus().getId()==1) {isHas=true;order=tmp;break;}
		}
		//если у пользователя нет брони в процессе создания
		if(!isHas) {
			System.out.println("hass no order");
			Order temp= new Order();
			System.out.println("add asatus and table");
			temp.setTable(table);
			System.out.println("add creator");
			temp.setCreator(user);
			System.out.println("create order");
			serviceOrder.create(temp);
			System.out.println("read cteated order");
			order = serviceOrder.repository.findByCreatorAndStatusAndDates(user,serviceStatus.read(1),null);
			System.out.println(order);
			System.out.println("add order to user");
			user.getMyOrders().add(order);
			System.out.println("update user");
			service.update(user);
			System.out.println("updated user: "+service.read(user.getId()));
			user=service.read(user.getId());
			order=user.getMyOrders().get(user.getMyOrders().size()-1);
			order.setDates(new Date(System.currentTimeMillis()));
			order.setStatus(serviceStatus.read(1));
			serviceOrder.update(order);
			System.out.println("updated user: "+service.read(user.getId()));	
		}
		
		
		return "redirect:/preorder";
	}
	@RequestMapping(path = "/deleteFood/{id}", method = RequestMethod.GET)
	public String deleteFoodFromOrder(Principal principal, @PathVariable("id") Long id) {
		User user = service.findByUsername(principal.getName());
		//проверка, есть ли не оформленный заказ
		Order order=user.getMyOrders().get(user.getMyOrders().size()-1);
		order.getFoods().remove(serviceFood.read(id));
		serviceOrder.update(order);
		return "redirect:/profile";
	}

}

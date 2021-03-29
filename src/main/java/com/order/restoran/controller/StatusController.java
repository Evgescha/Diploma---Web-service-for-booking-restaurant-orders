package com.order.restoran.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.order.restoran.entity.Status;
import com.order.restoran.service.StatusService;


@Controller
@RequestMapping("/status")
public class StatusController {

	@Autowired
	StatusService service;

	@GetMapping
	String getCategory(Model model) {
		List<Status> list = service.repository.findAll();
		model.addAttribute("list", list);
		return "status-list";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name = "id", required = false) Long id){
		if (id != null) {
			Status entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new Status());
		}
		return "status-add-edit";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/admin/control";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(Status entity) throws Exception {
		service.create(entity);
		return "redirect:/admin/control";
	}
}

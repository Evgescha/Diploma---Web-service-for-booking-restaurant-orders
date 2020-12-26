package com.order.restoran.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.order.restoran.entity.TypeTable;
import com.order.restoran.service.TypeTableService;


@Controller
@RequestMapping("/typeTable")
public class TypeTableController {

	@Autowired
	TypeTableService service;

	@GetMapping
	String getCategory(Model model) {
		List<TypeTable> list = service.repository.findAll();
		if (list == null)
			model.addAttribute("list", null);
		else
			model.addAttribute("list", list);
		return "typeTable-list";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name = "id", required = false) Long id){
		if (id != null) {
			TypeTable entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new TypeTable());
		}
		return "typeTable-add-edit";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/typeTable";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(TypeTable entity) throws Exception {
		service.create(entity);
		return "redirect:/typeTable";
	}
}

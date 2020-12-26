package com.order.restoran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.restoran.entity.Role;
import com.order.restoran.repository.RoleRepository;

@Service
public class RoleServiceImpl extends CrudImpl<Role> {

	public RoleRepository repository;

	@Autowired
	public RoleServiceImpl(RoleRepository repository) {
		super(repository);
		this.repository = repository;
	}

	public Role findByName(String name) {
		return repository.findByNameIgnoreCase(name);
	}

}

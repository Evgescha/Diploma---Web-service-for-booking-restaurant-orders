package com.order.restoran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.restoran.entity.Order;
import com.order.restoran.repository.OrderRepository;

@Service
public class OrderService extends CrudImpl<Order> {

	public OrderRepository repository;

	@Autowired
	public OrderService(OrderRepository repository) {
		super(repository);
		this.repository = repository;
	}

}

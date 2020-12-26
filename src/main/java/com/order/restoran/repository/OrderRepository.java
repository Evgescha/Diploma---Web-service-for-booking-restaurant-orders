package com.order.restoran.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.restoran.entity.Order;
import com.order.restoran.entity.Status;
import com.order.restoran.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByCreator(User creator);

	List<Order> findByStatus(Status status);
	
	Order findByCreatorAndDates(User creator,Date dates);
	
	Order findByCreatorAndStatus(User creator,Status status);
	Order findByCreatorAndStatusAndDates(User creator,Status status,Date dates);
}

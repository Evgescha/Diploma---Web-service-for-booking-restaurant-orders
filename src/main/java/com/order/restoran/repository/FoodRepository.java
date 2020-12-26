package com.order.restoran.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.restoran.entity.Food;
import com.order.restoran.entity.TypeFood;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
	
	List<Food> findByNameIgnoreCase(String name);

	List<Food> findByPrice(float price);

	List<Food> findByTypeFood(TypeFood typeFood);
}

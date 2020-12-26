package com.order.restoran.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.restoran.entity.MyTable;
import com.order.restoran.entity.TypeTable;

@Repository
public interface MyTableRepository extends JpaRepository<MyTable, Long> {
	

	List<MyTable> findByPeopleCount(int peopleCount);
	

	List<MyTable> findByTypeTable(TypeTable typeTable);

	
}

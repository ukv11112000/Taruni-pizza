package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.PizzaOrder;

public interface PizzaOrderRepository extends JpaRepository<PizzaOrder, Integer>{


	@Query("select p.id from PizzaOrder p")
	public List<Integer> getAllIds();

	public List<PizzaOrder> findAllByIsactive(boolean b);



}

package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Pizza;



public interface PizzaRepository extends JpaRepository<Pizza,Integer>{

	

	List<Pizza> findByTypeContaining(String type);

	List<Pizza> findByNameLike(String veggie);

	List<Pizza> findByNameStartingWith(String veggie);

	List<Pizza> findByTypeStartingWith(String string);

	List<Pizza> findByPriceBetween(Double minCost, Double maxCost);

	Pizza getById(int id);

	List<Pizza> findAll();

	public void deletePizzaById(int id);

	List<Pizza> findByNameContaining(String name);

	Pizza findByName(String name);

	

	
}
package com.example.demo.dto;

import com.example.demo.entity.Pizza;

public class PizzaDto {

	private Pizza pizza;

	public PizzaDto() {
		super();
	}

	public PizzaDto(Pizza pizza) {
		super();
		this.pizza = pizza;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
}

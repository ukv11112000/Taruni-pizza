package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Coupan;
import com.example.demo.entity.Pizza;
import com.example.demo.exception.CoupanNotFoundException;
import com.example.demo.exception.PizzaAlreadyExistsException;
import com.example.demo.exception.PizzaInvalidException;
import com.example.demo.exception.PizzaNotFoundException;
import com.example.demo.repository.CoupanRepository;
import com.example.demo.repository.PizzaRepository;


@Service
@Transactional
public class PizzaService {
	private static PizzaRepository pizzaRepository;
	private final CoupanRepository couponRepository;
	@Autowired
	public PizzaService(PizzaRepository pizzaRepo,CoupanRepository couponRepo) {
		this.pizzaRepository = pizzaRepo;
		this.couponRepository=couponRepo;
	}
	
	public boolean isValid(Pizza pizza) {
		if(pizza.getPrice()<0) {
			throw new PizzaInvalidException("Pizza Price cannot be Negitive");
		}
		return true;
	}
	
	public Pizza addPizza(Pizza pizza) {
		if(!isValid(pizza)) {
			return null;
		}
		if(pizzaRepository.findByName(pizza.getName())!=null) {
			throw new PizzaAlreadyExistsException();
		}
			
		return pizzaRepository.save(pizza);
	}
	
	public List<Pizza> viewPizzaList(){
		return pizzaRepository.findAll();
	}
	
	public String deletePizza(int id) {
		Pizza pizza=pizzaRepository.findById(id).orElse(null);
		if(pizza==null) {
			throw new PizzaNotFoundException("No Pizza Found with Id "+id);
		}
		pizzaRepository.delete(pizza);
		return "Pizza removed!";
	}
		
	public void updatePizza(Pizza pizza) {
		Pizza newpizza=pizzaRepository.findById(pizza.getId()).orElse(null);
		if(newpizza==null) {
			throw new PizzaNotFoundException("No Pizza Found with Id "+pizza.getId());
		}
		pizzaRepository.save(pizza);
	}
	
	public Pizza viewPizza(int id){
		Pizza pizza=pizzaRepository.findById(id).orElse(null);
		if(pizza==null) {
			throw new PizzaNotFoundException("No Pizza Found with Id "+id);
		}
		return pizza;
	}
	
	public List<Pizza> viewPizzaList(String name){
		 return pizzaRepository.findByNameContaining(name);
	}
	
	public List<Pizza>viewPizzaList(Double minCost,Double maxCost){
		return pizzaRepository.findByPriceBetween(minCost,maxCost);
	}

	public Pizza addPizzaCoupons(Pizza pcoupon) {
		return pizzaRepository.save(pcoupon);
		
	}

	public List<Pizza> viewPizzaCoupons() {
		return pizzaRepository.findAll();
	}

	public float pizzaCostAfterCoupon(int pid,int cid) {
		float finalcost=0;
		Pizza newpizza = pizzaRepository.findById(pid).orElse(null);
		if(newpizza==null) {
			throw new PizzaNotFoundException();
		}
		Coupan pcoupon= couponRepository.findById(cid).orElse(null);
		if(pcoupon==null) {
			throw new CoupanNotFoundException();
		}
		Double price=newpizza.getPrice();			
		finalcost=(float)(price-(float)(price/100)*pcoupon.getCoupanType());
		return finalcost;
	}
	
	public List<Pizza>findVeg() {
		return pizzaRepository.findByTypeStartingWith("veg");
		
	}
	
	public List<Pizza>findNonVeg() {
		return pizzaRepository.findByTypeStartingWith("non");
		
	}
	public static Pizza findbyId(int id) {
		Pizza p= pizzaRepository.findById(id).orElse(null);
		if(p==null) {
			throw new PizzaNotFoundException();
		}
		return p;
	}
	
}
package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PizzaDto;
import com.example.demo.entity.Pizza;
import com.example.demo.service.PizzaService;

@RestController

public class PizzaController {
	@Autowired
	private  PizzaService pizzaService;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@GetMapping("/pizza/viewpizzalist")
	public ResponseEntity<List<Pizza>>viewPizzaList1(){
		logger.info("Hitted the /pizza/viewpizzalist endpoint");
		List<Pizza> pizza = pizzaService.viewPizzaList();
		return new ResponseEntity<>(pizza,HttpStatus.OK);
	}
	
	@PostMapping(value="/pizza/add", consumes={"application/json"})
	public ResponseEntity<Pizza> addPizza(@RequestBody PizzaDto pizza){
		logger.info("Hitted the /pizza/add endpoint");
		Pizza newPizza = pizzaService.addPizza(pizza.getPizza());
		return new ResponseEntity<>(newPizza,HttpStatus.OK);
	}
	
	@PutMapping(value="/pizza/update", consumes={"application/json"})
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<Pizza> updatePizza(@RequestBody PizzaDto pizza){
		
		logger.info("Hitted the /pizza/update endpoint");
		pizzaService.updatePizza(pizza.getPizza());
		return new ResponseEntity<>(pizza.getPizza(),HttpStatus.OK);
	}
	
	@DeleteMapping("/pizza/delete/{id}")	
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<String> deletePizza(@PathVariable("id") int id){	
		logger.info("Hitted the /pizza/delete endpoint");
		String msg=pizzaService.deletePizza(id);	
		return new ResponseEntity<>(msg,HttpStatus.OK);	
	}
	@GetMapping("/pizza/viewpizzalist/{query}")
	public ResponseEntity<List<Pizza>>viewPizzaList(@PathVariable("query") String name){
		logger.info("Hitted the /pizza/viewpizzalist endpoint");
		List<Pizza> pizzas=pizzaService.viewPizzaList(name);
		return new ResponseEntity<>(pizzas,HttpStatus.OK);
	}
	
	@GetMapping("/pizza/viewpizza/{id}")
	//@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<Pizza>viewPizza(@PathVariable("id") int id){
		logger.info("Hitted the /pizza/viewpizza{"+id+"} endpoint");
		Pizza pizza=pizzaService.viewPizza(id);
		return new ResponseEntity<>(pizza,HttpStatus.OK);
	}
	
	@GetMapping(value="/pizza/viewpizza")
	public ResponseEntity<List<Pizza>>viewPizzaList(@RequestParam(value="id1")Double id1,@RequestParam(value="id2")Double id2){
		logger.info("Hitted the /pizza/viewpizza endpoint");
		List<Pizza> pizza=pizzaService.viewPizzaList(id1,id2);
		return new ResponseEntity<>(pizza,HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/pizza/type/veg")
	public ResponseEntity<List>searchByVeg(){
		List<Pizza> veg=this.pizzaService.findVeg();
		return new ResponseEntity<>(veg,HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("pizza/type/non-veg")
	public ResponseEntity<List> searchByNonveg(){
		List<Pizza> veg=this.pizzaService.findNonVeg();
		return new ResponseEntity<>(veg,HttpStatus.OK);
	}
	
	
	@PostMapping("/pizza/addpizzacoupons")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<Pizza> addPizzaCoupons(@RequestBody PizzaDto pcoupon){
		logger.info("Hitted the /pizza/adddpizzacoupons endpoint");
		Pizza newPcoupon = pizzaService.addPizzaCoupons(pcoupon.getPizza());
		return new ResponseEntity<>(newPcoupon,HttpStatus.CREATED);
	}
	
	@GetMapping("/pizza/viewpizzacoupons")
	public ResponseEntity<List<Pizza>> viewPizzaCoupons(){
		logger.info("Hitted the /pizza/viewpizzacoupons endpoint");
		List<Pizza> pcoupons=pizzaService.viewPizzaCoupons();
		return new ResponseEntity<>(pcoupons,HttpStatus.OK);
	}
	
	@GetMapping(value="/pizza/pizzacostaftercoupon")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<Float>pizzaCostAfterCoupon(@RequestParam(value="pid")int pid,@RequestParam(value="cid")int cid){
		float cost=pizzaService.pizzaCostAfterCoupon(pid,cid);
		logger.info("Hitted the /pizza/pizzacostaftercoupon endpoint");
		return new ResponseEntity<>(cost,HttpStatus.OK);
	}


	
	
	
}
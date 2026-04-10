package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PizzaOrderDto;
import com.example.demo.entity.PizzaOrder;
import com.example.demo.service.PizzaOrderService;

@RestController
public class PizzaOrderController {

	@Autowired
	private PizzaOrderService pizzaOrderService;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("/pizzaOrder/placeOrder")
	//@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<PizzaOrder> addOrder(@RequestBody PizzaOrderDto pord) {
		logger.info("Hitted the /pizzaOrder/placeOrder endpoint");
		return new ResponseEntity<>(pizzaOrderService.bookPizzaOrder(pizzaOrderService.DtoToObjectPO(pord)),HttpStatus.CREATED);
	}
	
	@PutMapping("/pizzaOrder/updateOrder")
	//@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<PizzaOrder> updateOrder(@RequestBody PizzaOrderDto pord) {
		logger.info("Hitted the /pizzaOrder/updateOrder endpoint");
		return new ResponseEntity<>(pizzaOrderService.updatePizzaOrder(pizzaOrderService.DtoToObjectPO(pord)),HttpStatus.OK);
	}
	@DeleteMapping("/pizzaOrder/deletOrder/{poid}")
	//@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<PizzaOrder> deleteOrder(@PathVariable int poid) {
		logger.info("Hitted the /pizzaOrder/deleteOrder endpoint");
		return new ResponseEntity<>(pizzaOrderService.cancelPizzaOrder(poid),HttpStatus.OK);
	}
	
	
	@GetMapping("/pizzaOrder/viewAll")
//	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<List<PizzaOrder>> getOrders() {
		logger.info("Hitted the /pizzaOrder/viewAll endpoint");
		return new ResponseEntity<>(pizzaOrderService.viewOrderList(),HttpStatus.OK);
	}
	@GetMapping("/pizzaOrder/viewAllActive")
//	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<List<PizzaOrder>> getActiveOrders() {
		logger.info("Hitted the /pizzaOrder/viewAllActive endpoint");
		return new ResponseEntity<>(pizzaOrderService.viewActiveOrderList(),HttpStatus.OK);
	}
	@GetMapping("/pizzaOrder/calculateTotal")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<List<PizzaOrder>> calculateTotal() {
		logger.info("Hitted the /pizzaOrder/calculateTotal endpoint");
		return new ResponseEntity<>(pizzaOrderService.calculateTotal(),HttpStatus.OK);
	}
	
	@GetMapping("/pizzaOrder/viewbyDate/{str}")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<List<PizzaOrder>> getOrdersbyDate(@PathVariable String str ) {
		logger.info("Hitted the /pizzaOrder/viewByDate endpoint");
		var date=LocalDate.parse(str);
		return new ResponseEntity<>(pizzaOrderService.viewOrderList(date),HttpStatus.OK);
	}
	@GetMapping("/pizzaOrder/test")
	public ResponseEntity<List<Integer>> getOrdersids() {
		logger.info("Hitted the /pizzaOrder/test endpoint");
		return new ResponseEntity<>(pizzaOrderService.allIds(),HttpStatus.OK);
	}
	
}

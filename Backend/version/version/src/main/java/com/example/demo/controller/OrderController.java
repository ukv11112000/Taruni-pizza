package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrdDto;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/order/add")
	//@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<Order> addOrder(@RequestBody OrdDto ord ,Principal principal){
		return new ResponseEntity<>(orderService.addOrder(ord,principal),HttpStatus.CREATED);
	}
	@GetMapping("/order/viewAll")
	//@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<List<Order>> viewOrder(){
	return new ResponseEntity<>(orderService.viewOrder(),HttpStatus.ACCEPTED);
	}
	@GetMapping("order/validateAll")
	public ResponseEntity<String> validateOrders(){
	return new ResponseEntity<>("validated",HttpStatus.ACCEPTED);
}
}

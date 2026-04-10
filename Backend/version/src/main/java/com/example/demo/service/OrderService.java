package com.example.demo.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrdDto;
import com.example.demo.dto.PizzaOrderDto;
import com.example.demo.entity.Order;
import com.example.demo.entity.PizzaOrder;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PizzaOrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	PizzaOrderRepository pizzaOrderRepository;
	
	@Autowired
	PizzaOrderService pizzaOrderService;
	
	@Autowired
	UserService userService;
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("null")
	public Order addOrder(OrdDto ord,Principal principal) {
		if(principal!=null) {
			logger.error("Login required ");
			ord.setOrderCustomerId(userService.getUserbyName(principal.getName()).getUserId());
		}
		else
			ord.setOrderCustomerId(1);
		List<Integer> pizzaOrders=ord.getPizzaorder();
		List<PizzaOrder> pizzaOrders1 = new ArrayList<PizzaOrder>();
		Order ord1=new Order(ord);
		for(Integer i : pizzaOrders) {
			PizzaOrder pizzaOrder=pizzaOrderService.findById(i);
			pizzaOrder.setIsactive(false);
			pizzaOrders1.add(pizzaOrder);
			pizzaOrderRepository.save(pizzaOrder);
		}
		ord1.setOrderTotal(ord.getOrderTotal());
		ord1.setPizzaorder(pizzaOrders1);
		return orderRepository.save(ord1);
	}

	public List<Order> viewOrder() {

		return orderRepository.findAll();
	}

	public Object validate() {
		List<Order> orders=this.viewOrder();
		for(Order order:orders) {
			if(order.getPizzaorder().isEmpty()) {
				orderRepository.delete(order);
			}
		}
		return null;
	}
}

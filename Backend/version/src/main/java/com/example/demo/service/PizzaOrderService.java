package com.example.demo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PizzaOrderDto;
import com.example.demo.entity.PizzaOrder;
import com.example.demo.exception.PizzaOrderInvalidException;
import com.example.demo.exception.PizzaOrderNotFoundException;
import com.example.demo.repository.PizzaOrderRepository;



@Service
public class PizzaOrderService {
	@Autowired
	private PizzaOrderRepository pizzaOrderRepository;
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private CoupanService coupanService;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public boolean isvalid(PizzaOrder pizzaOrder) {
		if(pizzaOrder.getQuantity()<=0) {
			logger.error("Quantity cannot be Negitive or Zero");
			throw new PizzaOrderInvalidException("Quantity cannot be Negitive or Zero");
		}
		if(!(pizzaOrder.getSize().equals("medium") || pizzaOrder.getSize().equals("large") || pizzaOrder.getSize().equals("small")))
			throw new PizzaOrderInvalidException("Size Cannot be "+pizzaOrder.getSize()+" it can be small or large or medium");
		if(!(pizzaOrder.getTransactionMode().equals("Online") || pizzaOrder.getTransactionMode().equals("Offline")))
			throw new PizzaOrderInvalidException("Transaction Mode Cannot be "+pizzaOrder.getTransactionMode()+"It Can either be Online?Offline");
		return true;
	}
	public PizzaOrder  bookPizzaOrder(PizzaOrder pizzaOrder) {
		if(!isvalid(pizzaOrder)) {
			return null;
		}
		if(pizzaOrder.getOrderDate()==null) {
		
			pizzaOrder.setOrderDate(LocalDate.now());
		}
		
		pizzaOrder.setIsactive(true);
		pizzaOrderRepository.save(pizzaOrder);
		this.calculateTotal();
		return pizzaOrderRepository.save(pizzaOrder);
	}
	public PizzaOrder updatePizzaOrder(PizzaOrder order) {
		if(!isvalid(order)) {
			return null;
		}
		PizzaOrder k= pizzaOrderRepository.findById(order.getBookingOrderId()).orElse(null);
		if(k==null) {
			throw new PizzaOrderNotFoundException("No PizzaOrder Found With Id"+order.getBookingOrderId());
		}
		k.setCoupan(order.getCoupan());
		k.setOrderDate(order.getOrderDate());
		k.setPizza(order.getPizza());
		k.setQuantity(order.getQuantity());
		k.setSize(order.getSize());
		k.setTotalCost(order.getTotalCost());
		k.setTransactionMode(order.getTransactionMode());
		this.calculateTotal();
		return pizzaOrderRepository.save(k);
	}
	public PizzaOrder cancelPizzaOrder(int  id) {
		PizzaOrder k=pizzaOrderRepository.findById(id).orElse(null);
		if(k==null) {
			logger.error("This Pizza Order dosenot exists");
			throw new PizzaOrderNotFoundException("This Pizza Order dosenot exists");
		}
		pizzaOrderRepository.delete(k);
		return k;
	}
	public PizzaOrder viewPizzaOrder(int  id) {
		
		return pizzaOrderRepository.findById(id).orElse(null);
	}
	public List<PizzaOrder> viewOrderList(){
		return pizzaOrderRepository.findAll();
	}
	public List<PizzaOrder> viewActiveOrderList(){
		return pizzaOrderRepository.findAllByIsactive(true);
	}
	public List<PizzaOrder> viewOrderList(LocalDate date){
		List<PizzaOrder> l= pizzaOrderRepository.findAll();
		List<PizzaOrder> l1=new ArrayList<>();
		int s=l.size();
		for(var i=0;i<s;i++) {
			PizzaOrder k=l.get(i);
			if(date.compareTo(k.getOrderDate())==0) {
				l1.add(k);
				
			}
			
		}
		return l1;
	}

	public List<PizzaOrder> calculateTotal(){
		for(PizzaOrder i: pizzaOrderRepository.findAll()) {
			String size= i.getSize();
			float quantity=i.getQuantity();

			float price= pizzaService.pizzaCostAfterCoupon(i.getPizza().getId(),i.getCoupan().getCoupanid());
			if(!size.equals("medium")) {
				if(size.equals("large")) {
					price+=30;
				}
				else if(size.equals("small")) {
					price-=30;
				}
			}
			double f=quantity*price;

		    f = round(f,2);
		    System.out.println(f);
			i.setTotalCost(f);
			pizzaOrderRepository.save(i);
		}
		return pizzaOrderRepository.findAll();
	}
	public List<Integer> allIds() {
		return pizzaOrderRepository.getAllIds();
		
	}
	public PizzaOrder DtoToObjectPO(PizzaOrderDto pord) {
		PizzaOrderDto pizzaOrderDto=pord;
		PizzaOrder pizzaOrder=new PizzaOrder(pizzaOrderDto);
		pizzaOrder.setPizza(pizzaService.findbyId(pizzaOrderDto.getPizza()));
		pizzaOrder.setCoupan(coupanService.findbyId(pizzaOrderDto.getCoupan()));
		return pizzaOrder;
	}
	public PizzaOrder findById(int id) {
		return pizzaOrderRepository.findById(id).orElse(null);
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}

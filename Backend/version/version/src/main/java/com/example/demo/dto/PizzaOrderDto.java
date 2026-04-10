package com.example.demo.dto;


import java.time.LocalDate;

import com.example.demo.entity.PizzaOrder;
import com.example.demo.service.CoupanService;
import com.example.demo.service.PizzaService;


public class PizzaOrderDto {
	
	private int bookingOrderId;
	private LocalDate orderDate;
	private String transactionMode;
	private int quantity;
	private String size;
	private double totalCost;
	private boolean isactive;
    private int pizza;
	private int coupan;
	public int getBookingOrderId() {
		return bookingOrderId;
	}
	public void setBookingOrderId(int bookingOrderId) {
		this.bookingOrderId = bookingOrderId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public int getPizza() {
		return pizza;
	}
	public void setPizza(int pizza) {
		this.pizza = pizza;
	}
	public int getCoupan() {
		return coupan;
	}
	public void setCoupan(int coupan) {
		this.coupan = coupan;
	}
	
	public PizzaOrderDto(int bookingOrderId, LocalDate orderDate, String transactionMode, int quantity, String size,
			double totalCost, int pizza, int coupan,boolean isactive) {
		super();
		this.bookingOrderId = bookingOrderId;
		this.orderDate = orderDate;
		this.transactionMode = transactionMode;
		this.quantity = quantity;
		this.size = size;
		this.totalCost = totalCost;
		this.pizza = pizza;
		this.coupan = coupan;
		this.isactive=true;
	}
	public PizzaOrderDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

}

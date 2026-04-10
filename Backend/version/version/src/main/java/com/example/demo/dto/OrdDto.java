package com.example.demo.dto;


import java.util.List;


public class OrdDto {


	private int orderid;
	private String orderType;
	private String orderDescription;
	private int orderCustomerId;
	private boolean success;
	private int orderTotal;
	private List<Integer> pizzaorder;
	public List<Integer> getPizzaorder() {
		return pizzaorder;
	}
	public void setPizzaorder(List<Integer> pizzaorder) {
		this.pizzaorder = pizzaorder;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderId) {
		this.orderid = orderId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	public int getOrderCustomerId() {
		return orderCustomerId;
	}
	public void setOrderCustomerId(int orderCustomerId) {
		this.orderCustomerId = orderCustomerId;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean isSuccess) {
		this.success = isSuccess;
	}
	public int getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}
	
}

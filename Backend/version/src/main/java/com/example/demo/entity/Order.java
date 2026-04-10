package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.demo.dto.OrdDto;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;



@Entity
@Table(name="Ord")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
	@GenericGenerator(name = "seq", strategy="increment")
	private int orderid;
	private String orderType;
	private String orderDescription;
	private int orderCustomerId;
	private boolean success;
	private int orderTotal;
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PizzaOrderId")
	private List<PizzaOrder> pizzaOrders;
	public List<PizzaOrder> getPizzaorder() {
		return pizzaOrders;
	}
	public void setPizzaorder(List<PizzaOrder> pizzaorder) {
		this.pizzaOrders = pizzaorder;
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
	public Order(OrdDto ord) {
		this.orderType = ord.getOrderType();
		this.orderDescription = ord.getOrderDescription();
		this.orderCustomerId = ord.getOrderCustomerId();
		this.success=ord.isSuccess();
	}
	public Order(int orderid, String orderType, String orderDescription, int orderCustomerId,
			List<PizzaOrder> pizzaorder,int orderTotal) {
		super();
		this.orderid = orderid;
		this.orderType = orderType;
		this.orderDescription = orderDescription;
		this.orderCustomerId = orderCustomerId;
		this.pizzaOrders = pizzaorder;
		this.orderTotal=orderTotal;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
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

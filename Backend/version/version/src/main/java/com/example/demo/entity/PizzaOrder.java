package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.demo.dto.PizzaOrderDto;

@Entity
@Table(name = "PizzaOrder")
public class PizzaOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @GenericGenerator(name = "seq", strategy = "increment")
    @Column(name = "pOrdId")
    private int bookingOrderId;

    private LocalDate orderDate;
    private String transactionMode;
    private int quantity;
    private String size;
    private double totalCost;
    private boolean isactive;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "pizza_id", referencedColumnName = "id")
    private Pizza pizza;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "coupan_id", referencedColumnName = "coupanid")
    private Coupan coupan;

    public PizzaOrder() {
        super();
    }

    public PizzaOrder(int bookingOrderId, LocalDate orderDate, String transactionMode, int quantity, String size,
                      double totalCost, Pizza pizza, Coupan coupan) {
        super();
        this.bookingOrderId = bookingOrderId;
        this.orderDate = orderDate;
        this.transactionMode = transactionMode;
        this.quantity = quantity;
        this.size = size;
        this.totalCost = totalCost;
        this.pizza = pizza;
        this.coupan = coupan;
    }

    public PizzaOrder(PizzaOrderDto pizzaOrderDto) {
        super();
        this.bookingOrderId = pizzaOrderDto.getBookingOrderId();
        this.orderDate = pizzaOrderDto.getOrderDate();
        this.transactionMode = pizzaOrderDto.getTransactionMode();
        this.quantity = pizzaOrderDto.getQuantity();
        this.size = pizzaOrderDto.getSize();
        this.totalCost = pizzaOrderDto.getTotalCost();
        this.isactive = pizzaOrderDto.isIsactive();
    }

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

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Coupan getCoupan() {
        return coupan;
    }

    public void setCoupan(Coupan coupan) {
        this.coupan = coupan;
    }
}

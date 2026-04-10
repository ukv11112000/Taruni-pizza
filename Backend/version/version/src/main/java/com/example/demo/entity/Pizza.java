package com.example.demo.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Pizza")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pizza implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,updatable=false)
	private int id;
	private String name;
	private String type;
	private String description;
	private Double price;
	private String url;
	

	@OneToMany(cascade=CascadeType.MERGE)
	@JoinColumn(name="coupanPizzaid",referencedColumnName="id")
	private List<Coupan> coupan;  
	
	public Pizza() {}

	public Pizza(String name, String type, String description, Double price, String url) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.price = price;
		this.url = url;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public String getName() {
		return  name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	

	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price=price;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url=url;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type=type;
	}
	
	public List<Coupan> getCoupan() {
		return coupan;
	}
	
	public void setCoupan(List<Coupan> coupan) {
		this.coupan=coupan;
	}
	
	
}

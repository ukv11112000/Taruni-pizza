package com.example.demo.dto;

import com.example.demo.entity.Coupan;

public class CoupanDto {

	private Coupan coupan;

	public Coupan getCoupan() {
		return coupan;
	}

	public void setCoupan(Coupan coupan) {
		this.coupan = coupan;
	}

	public CoupanDto(Coupan coupan) {
		super();
		this.coupan = coupan;
	}

	public CoupanDto() {
		super();
	
	}
	
}

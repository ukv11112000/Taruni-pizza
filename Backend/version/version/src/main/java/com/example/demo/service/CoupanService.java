package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Coupan;
import com.example.demo.exception.CoupanNotFoundException;
import com.example.demo.exception.NoCoupanFoundException;
import com.example.demo.repository.CoupanRepository;

@Service
public class CoupanService {
	@Autowired
	public  CoupanRepository coupanRepository;
	

	public Coupan addCoupans(Coupan coupan) {
		return coupanRepository.save(coupan);
	}
	public Coupan editCoupans(Coupan coupan) {
		Coupan coupan1=coupanRepository.findById(coupan.getCoupanid()).orElse(null);
		if(coupan1==null) {
			throw new CoupanNotFoundException("No Coupan Found with Id"+coupan.getCoupanid());
		}
		return coupanRepository.save(coupan);
	}
	
	public List<Coupan> viewCoupans() {
		List<Coupan>l= coupanRepository.findAll();
		if(l.isEmpty()) {
			throw new NoCoupanFoundException();
		}
		return l;
	}
	public String deleteCoupons(int id) {
		var coupan= coupanRepository.findById(id).orElse(null);
		if(coupan==null) {
			throw new CoupanNotFoundException("No Coupan Found with Id"+id);
		}
		coupanRepository.delete(coupan);
		return "Coupan Deleted";
	}
	public    Coupan findbyId(int id) {
		Coupan coupan=coupanRepository.findById(id).orElse(null);
		if(coupan==null) {
			throw new CoupanNotFoundException("No Coupan Found with Id"+id);
		}
		return coupan;
	}
	
}

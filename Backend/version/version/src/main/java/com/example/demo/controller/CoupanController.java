package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CoupanDto;
import com.example.demo.entity.Coupan;
import com.example.demo.service.CoupanService;

@RestController
public class CoupanController {

	@Autowired
	private CoupanService coupanService;
	
	@PostMapping("/coupan/add")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<Coupan> addCoupan(@RequestBody CoupanDto coup) {
		return new ResponseEntity<>(coupanService.addCoupans(coup.getCoupan()),HttpStatus.CREATED);
	}
	
	@GetMapping("/coupan/viewAll")
	public ResponseEntity<List<Coupan>> viewCoupans(){
		return new ResponseEntity<>(coupanService.viewCoupans(),HttpStatus.OK);
	}
	
	@PostMapping("/coupan/edit")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<Coupan> editCoupan(@RequestBody CoupanDto coup) {
		return new ResponseEntity<>(coupanService.editCoupans(coup.getCoupan()),HttpStatus.OK);
	}
	
	@DeleteMapping("/coupan/delete/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<String> deleteCoupon(@PathVariable("id") Integer id){
		String msg=coupanService.deleteCoupons(id);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
}

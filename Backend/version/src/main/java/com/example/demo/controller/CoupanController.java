package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CoupanDto;
import com.example.demo.entity.Coupan;
import com.example.demo.service.CoupanService;

@RestController
public class CoupanController {

	@Autowired
	private CoupanService coupanService;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@PostMapping(value="/coupan/add", consumes={"application/json"})
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<Coupan> addCoupan(@RequestBody CoupanDto coup) {
		logger.info("Hitted the /coupan/add endpoint");
		return new ResponseEntity<>(coupanService.addCoupans(coup.getCoupan()),HttpStatus.CREATED);
	}
	
	@GetMapping("/coupan/viewAll")
	public ResponseEntity<List<Coupan>> viewCoupans(){
		logger.info("Hitted the /coupan/viewAll endpoint");
		return new ResponseEntity<>(coupanService.viewCoupans(),HttpStatus.OK);
	}
	
	
	@PutMapping(value="/coupan/edit", consumes={"application/json"})
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<Coupan> editCoupan(@RequestBody CoupanDto coup) {
		logger.info("Hitted the /coupan/edit endpoint");
		return new ResponseEntity<>(coupanService.editCoupans(coup.getCoupan()),HttpStatus.OK);
	}
	
	@DeleteMapping("/coupan/delete/{id}")
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<String> deleteCoupon(@PathVariable("id") Integer id){
		logger.info("Hitted the /coupan/delete{"+id+"} endpoint");
		String msg=coupanService.deleteCoupons(id);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
}

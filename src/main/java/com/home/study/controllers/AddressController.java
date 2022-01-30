package com.home.study.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.study.entities.Address;
import com.home.study.services.AddresServices;

@RestController
@RequestMapping("/users/{userId}/profiles/{profileId}/addresses")
public class AddressController {
	
	@Autowired
	private AddresServices addresServices;
	
	@GetMapping
	public ResponseEntity<List<Address>> findAddressByProfilerAndUserName(@PathVariable("userId") Integer userId,
			@PathVariable("profileId") Integer profileId) {
		
		return new ResponseEntity<List<Address>>(addresServices.findAddressByProfilerAndUserName(userId,profileId), HttpStatus.OK);
	}
	
	@PostMapping
	private ResponseEntity<Address> createAddress(@RequestBody Address address, @PathVariable("userId") Integer userId,
			@PathVariable("profileId") Integer profileId) {
		
		return new ResponseEntity<Address>(addresServices.createAddress(userId, profileId, address), HttpStatus.CREATED);
	}
}

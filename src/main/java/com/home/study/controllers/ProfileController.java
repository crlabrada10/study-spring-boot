package com.home.study.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.study.entities.Profile;
import com.home.study.services.ProfileService;

/**
 * @author crlab
 *
 */
@RestController
@RequestMapping("/users/{userId}/profiles")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	@GetMapping(value = "/{idProfile}")
	public ResponseEntity<Profile> findProfileById(@PathVariable("userId")Integer userdId, @PathVariable("idProfile") Integer idProfile) {
		return new ResponseEntity<Profile>(profileService.findGetByUserAndProfileById(userdId, idProfile), HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Profile> create(@PathVariable("userId")Integer userdId, @RequestBody Profile profile) {
		return new ResponseEntity<Profile>(profileService.save(userdId, profile), HttpStatus.CREATED);
	}
}

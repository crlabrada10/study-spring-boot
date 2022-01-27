package com.home.study.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.study.model.User;
import com.home.study.services.UserServiceListJFake;

/**
 * @author crlabrada10
 *
 */
@RestController
@RequestMapping("/v1/users")
public class UserControllerListJFake {

	@Autowired
	private UserServiceListJFake userService;

	@GetMapping
	public ResponseEntity<List<User>> getUser(@RequestParam(required = false, value= "startWith") String startWith) {
		return new ResponseEntity<List<User>>(userService.getUser(startWith), HttpStatus.OK);
	}
	//@GetMapping(value = "{username}")
	@RequestMapping(method = RequestMethod.GET, value = "{username}")
	///*@PathParam("username") String username*/
	public ResponseEntity<User> findUserByUsername(@PathVariable("username") String username) {
		return new ResponseEntity<User>(userService.getUserByUsername(username), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{username}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("username")String username) {
		return new ResponseEntity<User>(userService.updateUser(user, username), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username")String username) {
		userService.deleteUser(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

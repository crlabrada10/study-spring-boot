package com.home.study.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.study.entities.User;
import com.home.study.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//import io.micrometer.core.annotation.Timed;

/**
 * @author crlab
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping()
	//@Timed(value = "get.user") //para definir tus metricas
	public ResponseEntity<Page<User>> getUser(@RequestParam(required = false, value= "page",defaultValue = "0") int page, 
											  @RequestParam(required = false, value= "size",defaultValue = "10") int size){
		return new ResponseEntity<Page<User>>(userService.getUsers(page,size), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/usernames")
	public ResponseEntity<Page<String>> getNames(@RequestParam(required = false, value= "page",defaultValue = "0") int page, 
											  @RequestParam(required = false, value= "size",defaultValue = "10") int size){
		return new ResponseEntity<Page<String>>(userService.getUsernames(page,size), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{userId}")
	@ApiOperation(value ="Return a user for a userId ", response =User.class)
	@ApiResponses(value = {
			 @ApiResponse(code = 200, message ="The record was found") ,
			 @ApiResponse(code = 404, message = "The record was not found")
			 })

	public ResponseEntity<User> getUserById(@PathVariable ("userId") Integer id){
		return new ResponseEntity<User>(userService.findUserById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/username/{username}")
	public ResponseEntity<User> findByUsername(@PathVariable("username") String username){
		return new ResponseEntity<User>(userService.findUserUsername(username), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> authenticate(@RequestBody User user){
		return new ResponseEntity<User>(userService.findUserUsernameAndPassword(user.getUsername(), user.getPassword()), HttpStatus.OK);
	}
	
	@DeleteMapping( "/username/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username){
		userService.deleteUserByUsername(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

package com.home.study.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.study.entities.Role;
import com.home.study.services.RoleService;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	private static final Logger log = LoggerFactory.getLogger(RoleController.class);

	@GetMapping() 
	public ResponseEntity<List<Role>> getRoles() {
		//Sirve para obtener el usuario que esta autenticado en la aplicación
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("Name {} ", authentication.getName());
		log.info("Principal {} ", authentication.getPrincipal());
		log.info("Credential {} ", authentication.getCredentials());
		log.info("Roles {} ", authentication.getAuthorities().toString());
		log.info("Details {}", authentication.getDetails());
		return new ResponseEntity<List<Role>>(roleService.getRoles(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Role> saveRole(@RequestBody Role role){
		return new ResponseEntity<Role>(roleService.save(role), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{roleId}")
	public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable("roleId") Integer id){
		return new ResponseEntity<Role>(roleService.updateRole(id,role), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable("id") Integer identificador){
		roleService.deleteRole(identificador);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}

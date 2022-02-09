package com.home.study.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.study.entities.Role;
import com.home.study.entities.User;
import com.home.study.services.RoleService;

/**
 * @author crlabrada10
 *
 */
@RestController
@RequestMapping("/users/roles")
public class UserInRoleController {
	
	@Autowired
	private RoleService roleService;
	
	
	private static final Logger log = LoggerFactory.getLogger(UserInRoleController.class);

	@GetMapping("/{roleName}")
	public ResponseEntity<List<User>> getRoles(@PathVariable("roleName") String rolName) {
		//Sirve para obtener el usuario que esta autenticado en la aplicación
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("Name {} ", authentication.getName());
		log.info("Principal {} ", authentication.getPrincipal());
		log.info("Credential {} ", authentication.getCredentials());
		log.info("Roles {} ", authentication.getAuthorities().toString());
		log.info("Details {}", authentication.getDetails());
		
		return new ResponseEntity<List<User>>(roleService.getUserByRole(rolName), HttpStatus.OK);
	}
}

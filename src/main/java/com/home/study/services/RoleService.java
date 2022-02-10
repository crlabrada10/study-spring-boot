package com.home.study.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.study.entities.Role;
import com.home.study.entities.User;
import com.home.study.model.AuditDetails;
import com.home.study.model.HomeSecurityRule;
import com.home.study.repositories.RoleRepository;
import com.home.study.repositories.UserInRoleRepository;

/**
 * @author crlabrada10
 *
 */
@Service()
@HomeSecurityRule
public class RoleService {

	@Autowired
	private RoleRepository repository;

	@Autowired
	private UserInRoleRepository userInRoleRepository;

	private static final Logger log = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;

	private ObjectMapper mapper = new ObjectMapper();
	// Se utiliza cuando se configura el @EnableGlobalMethodSecurity(securedEnabled
	// = true) para metodos
	// @Secured({"ROLE_ADMIN"})
	// Se utiliza cuando se configura el @EnableGlobalMethodSecurity(jsr250Enabled =
	// true)
	// @RolesAllowed({"ROLE_ADMIN"})

	// @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	// @PostAuthorize("hasRole('ROLE_ADMIN')")

	// @HomeSecurityRule
	public List<User> getUserByRole(String rolName) {
		log.info("El rolname es {}", rolName);
		return userInRoleRepository.findUserByRole(rolName);
	}

	public List<Role> getRoles() {
		return repository.findAll();
	}

	public Role save(Role role) {
		Role roleCreate = repository.save(role);

		AuditDetails details = new AuditDetails(SecurityContextHolder.getContext().getAuthentication().getName(),
				roleCreate.getName());
		try {
			kafkaTemplate.send("home-topic", mapper.writeValueAsString(details));
		} catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error parsing the message");
		}
		return roleCreate;
	}

	public Role updateRole(Integer id, Role role) {
		Optional<Role> result = repository.findById(id);
		if (result.isPresent()) {
			return repository.save(role);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d no existe", id));
		}
	}

	public void deleteRole(Integer id) {
		Role role = findRoleById(id);
		repository.delete(role);
	}

	public Role findRoleById(Integer id) {
		Optional<Role> result = repository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d no existe", id));
		}
	}
}

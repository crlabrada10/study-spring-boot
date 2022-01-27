package com.home.study.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.home.study.entities.Role;
import com.home.study.repositories.RoleRepository;

/**
 * @author crlabrada10
 *
 */
@Service()
public class RoleService {
	
	@Autowired
	private RoleRepository repository;
	
	public List<Role> getRoles() {
		return repository.findAll();
	}
	
	public Role save(Role role) {
		return repository.save(role);
	}
	
	public Role updateRole(Integer id, Role role) {
		Optional<Role> result = repository.findById(id);
		if(result.isPresent()) {
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
		if(result.isPresent()) {
			return result.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d no existe", id));
		}
	}
}

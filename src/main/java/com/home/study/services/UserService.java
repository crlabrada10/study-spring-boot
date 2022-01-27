package com.home.study.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.home.study.entities.User;
import com.home.study.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Page<User> getUsers(int page, int size) {
		return  userRepository.findAll(PageRequest.of(page, size));
	}
	
	public Page<String> getUsernames(int page, int size) {
		return  userRepository.findUsernames(PageRequest.of(page, size));
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}

	public User updateRole(Integer id, User user) {
		Optional<User> result = userRepository.findById(id);
		if (result.isPresent()) {
			return userRepository.save(user);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User id %d no existe", id));
		}
	}

	public void deleteRole(Integer id) {
		User role = findUserById(id);
		userRepository.delete(role);
	}

	public User findUserById(Integer id) {
		return userRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d no existe", id)));
	}
	
	public User findUserUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d no existe", username)));
	}
	
	public User findUserUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %s no existe", username)));
	}
}

package com.home.study.repositories;

import java.util.Optional;

import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.home.study.entities.User;

/**
 * @author crlab
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>{

	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByUsernameAndPassword(String username, String password);
	
	/***
	 * Se puede usar el PageRequest o el Pageable de org.springframework.data.domain.Pageable
	 * {@link PageRequest} hereda de {@link AbstractPageRequest} que implementa la interfaz Pageable
	 * */
	@Query("SELECT u.username FROM User u ")
	public Page<String> findUsernames(PageRequest pageRequest);
}

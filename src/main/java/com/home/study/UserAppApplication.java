package com.home.study;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;
import com.home.study.entities.Role;
import com.home.study.entities.User;
import com.home.study.entities.UserInRole;
import com.home.study.repositories.RoleRepository;
import com.home.study.repositories.UserInRoleRepository;
import com.home.study.repositories.UserRepository;

@SpringBootApplication
public class UserAppApplication implements ApplicationRunner{
	
	@Autowired
	private Faker faker;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	
	private static final Logger log = LoggerFactory.getLogger(UserAppApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Role roles [] = { new Role("ADMIN"), new Role ("SUPPORT"), new Role("USER")};
		for (int i = 0; i < roles.length; i++) {
			roleRepository.save(roles[i]);
		}
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
			User u = userRepository.save(user);
			
			
			UserInRole userInRole = new UserInRole();
			
			userInRole.setUser(u);
			userInRole.setRole(roles[new Random().nextInt(3)]);
			log.info("user created username {} password {} role {}" , u.getUsername(), u.getPassword(), userInRole.getRole().getName());
			userInRoleRepository.save(userInRole);
		
		}
	}

}

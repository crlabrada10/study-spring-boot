package com.home.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;
import com.home.study.entities.User;
import com.home.study.repositories.UserRepository;

@SpringBootApplication
public class UserAppApplication implements ApplicationRunner{
	
	@Autowired
	private Faker faker;
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (int i = 0; i < 1000; i++) {
			User user = new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
			user.setProfile(null);
			userRepository.save(user);
		}
	}

}

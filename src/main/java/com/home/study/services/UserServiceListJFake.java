package com.home.study.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.javafaker.Faker;
import com.home.study.model.User;

/**
 * @author crlabrada10
 *
 */
@Service
public class UserServiceListJFake {

	@Autowired
	private Faker faker;

	private List<User> user = new ArrayList<>();

	@PostConstruct
	public void init() {
		for (int i = 0; i < 10; i++) {
			user.add(new User(faker.funnyName().name(), faker.name().username(), faker.dragonBall().character()));
		}

	}

	public List<User> getUser(String startWith) {
		if (startWith != null) {
			return user.stream().filter((User u)-> u.getUsername().startsWith(startWith)).collect(Collectors.toList());
		} else {
			return user;
		}
	}

	public User getUserByUsername(String userName) {
		return user.stream().filter((User u) -> u.getUsername().equals(userName)).findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("username %s no found", userName)));
	}

	public User createUser(User u) {
		if (user.stream().anyMatch((User user) -> user.getUsername().equals(u.getUsername()))) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					String.format("User %s already exist", u.getUsername()));
		}
		user.add(u);
		return u;
	}

	public User updateUser(User user, String username) {
		User userToBeUpdate = getUserByUsername(username);
		userToBeUpdate.setNickName(user.getNickName());
		// userToBeUpdate.setUsername(user.getUsername());
		userToBeUpdate.setPassword(user.getPassword());

		return userToBeUpdate;
	}

	public void deleteUser(String username) {
		User u = getUserByUsername(username);
		user.remove(u);
		// getUser().removeIf(x-> x.getUsername().equals(username)) ;
	}
}

package com.home.study.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.home.study.entities.User;
import com.home.study.entities.UserInRole;
import com.home.study.repositories.UserInRoleRepository;
import com.home.study.repositories.UserRepository;

/**
 * @author crlabrada10
 *
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	/** 
	 * En este ejemplo no se verifica la contraseña que el usuario pasa
	 * */
	@Override	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = userRepository.findByUsername(username);
		if (optional.isPresent()) {
			User user = optional.get();
			List<UserInRole> userInRoles = userInRoleRepository.findByUser(user);
			String[] roles = userInRoles.stream().map(r -> r.getRole().getName()).toArray(String[]::new);

			return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
					.password(passwordEncoder.encode(user.getPassword())).roles(roles).build();
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
	}

}

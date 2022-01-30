package com.home.study.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.home.study.entities.Profile;
import com.home.study.entities.User;
import com.home.study.repositories.ProfileRepository;
import com.home.study.repositories.UserRepository;

/**
 * @author crlab
 *
 */
@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private UserRepository userRepository;

	public Profile save(Integer userId, Profile p) {
		User u = userRepository.findById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User id %d no existe", userId)));
		p.setUser(u);
		return profileRepository.save(p);
	}
	
	public Profile findGetByUserAndProfileById(Integer userId, Integer idProfile) {
		Optional<Profile> result = profileRepository.findByUserIdAndProfileId(userId, idProfile);
		if(result.isPresent()) {
			return result.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile not found for user %d and profile %d", userId,idProfile));
		}

	}
}

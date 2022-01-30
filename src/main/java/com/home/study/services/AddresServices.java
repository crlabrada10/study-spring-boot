package com.home.study.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.study.entities.Address;
import com.home.study.entities.Profile;
import com.home.study.repositories.AddressRepository;

/**
 * @author Carlos R. Labrada
 *
 */
@Service
public class AddresServices {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ProfileService profileService;
	
	public List<Address> findAddressByProfilerAndUserName(Integer userId, Integer profileId) {
		return addressRepository.findByProfileId(userId,profileId);
	}

	public Address createAddress(Integer userId, Integer profileId, Address address) {
		Profile result = profileService.findGetByUserAndProfileById(userId, profileId);
		address.setProfile(result);
		return addressRepository.save(address);
	}

}

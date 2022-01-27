package com.home.study.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.home.study.entities.Profile;

/**
 * @author crlab
 *
 */
@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer>{

}

package com.home.study.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.home.study.entities.Profile;

/**
 * @author crlab
 *
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>{
	
	@Query("SELECT p FROM Profile p WHERE p.user.id=?1 AND  p.id=?2")
	Optional<Profile> findByUserIdAndProfileId(Integer userId, Integer idProfile);

}

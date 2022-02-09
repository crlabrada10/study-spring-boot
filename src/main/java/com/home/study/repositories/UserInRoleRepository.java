package com.home.study.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.home.study.entities.User;
import com.home.study.entities.UserInRole;

/**
 * @author crlabrada10
 *
 */
@Repository
public interface UserInRoleRepository extends JpaRepository<UserInRole, Integer> {

	@Query("Select u.user FROM UserInRole u WHERE u.role.name=?1")
	public List<User> findUserByRole(String roleName);
	
	
	public List<UserInRole> findByUser(User user);
}

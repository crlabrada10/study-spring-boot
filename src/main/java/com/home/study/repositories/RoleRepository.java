package com.home.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.study.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}

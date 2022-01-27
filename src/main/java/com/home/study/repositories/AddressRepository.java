package com.home.study.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.home.study.entities.Address;

/**
 * @author crlab
 *
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer>{

}

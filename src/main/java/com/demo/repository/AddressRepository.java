package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {

	
}

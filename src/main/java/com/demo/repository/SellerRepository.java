package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.demo.domain.AccountStatus;
import com.demo.model.Seller;

import java.util.*;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

	Seller findByEmail(String email);

 List<Seller>findByAccountStatus(AccountStatus status);
 
}

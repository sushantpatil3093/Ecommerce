package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Deal;
import com.demo.model.Home;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {

}

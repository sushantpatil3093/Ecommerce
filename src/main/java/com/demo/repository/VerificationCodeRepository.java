package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.VerificationCode;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

	
	VerificationCode findByEmail(String email);

   // VerificationCode findByEmail(String email);
  //  List<VerificationCode> findByEmail(String email); // ✅ handles multiple rows

   VerificationCode findByOtp(String otp); 
}


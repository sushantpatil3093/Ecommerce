package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.SellerReport;

public interface SellerReportRepository  extends JpaRepository<SellerReport, Long>{
SellerReport findBySellerId(Long sellerId);
}

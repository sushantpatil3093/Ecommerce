package com.demo.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String code;
	private double discountPercentage;
	private LocalDate validityStartDate;
	private LocalDate validityEndDate;
	
	private double minimumOrderValue;

	private boolean isActive=true;
	
	@OneToMany(mappedBy = "usedCoupen")
	private Set<User>usedByUsers=new HashSet<>();

	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coupon(Long id, String code, double discountPercentage, LocalDate validityStartDate,
			LocalDate validityEndDate, double minimumOrderValue, boolean isActive, Set<User> usedByUsers) {
		super();
		this.id = id;
		this.code = code;
		this.discountPercentage = discountPercentage;
		this.validityStartDate = validityStartDate;
		this.validityEndDate = validityEndDate;
		this.minimumOrderValue = minimumOrderValue;
		this.isActive = isActive;
		this.usedByUsers = usedByUsers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public LocalDate getValidityStartDate() {
		return validityStartDate;
	}

	public void setValidityStartDate(LocalDate validityStartDate) {
		this.validityStartDate = validityStartDate;
	}

	public LocalDate getValidityEndDate() {
		return validityEndDate;
	}

	public void setValidityEndDate(LocalDate validityEndDate) {
		this.validityEndDate = validityEndDate;
	}

	public double getMinimumOrderValue() {
		return minimumOrderValue;
	}

	public void setMinimumOrderValue(double minimumOrderValue) {
		this.minimumOrderValue = minimumOrderValue;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<User> getUsedByUsers() {
		return usedByUsers;
	}

	public void setUsedByUsers(Set<User> usedByUsers) {
		this.usedByUsers = usedByUsers;
	}

	
	
}

package com.demo.model;

import java.util.HashSet;
import java.util.Set;


import com.demo.domain.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonEncoding;

import jakarta.annotation.Generated;
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
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	 
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private String password;

	private String email;
	
	private String fullName;
	
	private String mobile;
	
	private USER_ROLE role =USER_ROLE.ROLE_CUSTOMER;
	
	@OneToMany
	private Set<Address>address=new HashSet<>();
	
	@ManyToMany
	@JsonIgnore
	private Set<Coupon>usedCoupen=new HashSet<>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String password, String email, String fullName, String mobile, USER_ROLE role,
			Set<Address> address, Set<Coupon> usedCoupen) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.mobile = mobile;
		this.role = role;
		this.address = address;
		this.usedCoupen = usedCoupen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public USER_ROLE getRole() {
		return role;
	}

	public void setRole(USER_ROLE role) {
		this.role = role;
	}

	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}

	public Set<Coupon> getUsedCoupen() {
		return usedCoupen;
	}

	public void setUsedCoupen(Set<Coupon> usedCoupen) {
		this.usedCoupen = usedCoupen;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", email=" + email + ", fullName=" + fullName + ", mobile="
				+ mobile + ", role=" + role + ", address=" + address + ", usedCoupen=" + usedCoupen + "]";
	}

	
	
	
	
}

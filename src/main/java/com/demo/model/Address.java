package com.demo.model;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String name;
	
	private String locality;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String pincode;
	
	private String mobile;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(Long id, String name, String locality, String address, String city, String state, String pincode,
			String mobile) {
		super();
		this.id = id;
		this.name = name;
		this.locality = locality;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.mobile = mobile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", locality=" + locality + ", address=" + address + ", city="
				+ city + ", state=" + state + ", pincode=" + pincode + ", mobile=" + mobile + "]";
	}

	
	
}

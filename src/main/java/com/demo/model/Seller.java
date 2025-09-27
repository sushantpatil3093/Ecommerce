package com.demo.model;

import com.demo.domain.AccountStatus;
import com.demo.domain.USER_ROLE;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String sellerName;
	
	private String mobile;
	
	
	@Column(unique = true,nullable = false)
	private String email;
	
	private String password;
	
	@Embedded
	private BusinessDetails businessDetails=new BusinessDetails();
	
	@Embedded
	private BankDetails bankDetails=new BankDetails();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address pickupAddress=new Address();
	
	private String GSTIN;
	
	
	private USER_ROLE role=USER_ROLE.ROLE_SELLER;
	
	private boolean isEmailVerified=false;
	
	private AccountStatus accountStatus=AccountStatus.PENDING_VERIFICATION;

	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seller(Long id, String sellerName, String mobile, String email, String password,
			BusinessDetails businessDetails, BankDetails bankDetails, Address pickupAddress, String gSTIN,
			USER_ROLE role, boolean isEmailVerified, AccountStatus accountStatus) {
		super();
		this.id = id;
		this.sellerName = sellerName;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.businessDetails = businessDetails;
		this.bankDetails = bankDetails;
		this.pickupAddress = pickupAddress;
		GSTIN = gSTIN;
		this.role = role;
		this.isEmailVerified = isEmailVerified;
		this.accountStatus = accountStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BusinessDetails getBusinessDetails() {
		return businessDetails;
	}

	public void setBusinessDetails(BusinessDetails businessDetails) {
		this.businessDetails = businessDetails;
	}

	public BankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

	public Address getPickupAddress() {
		return pickupAddress;
	}

	public void setPickupAddress(Address pickupAddress) {
		this.pickupAddress = pickupAddress;
	}

	public String getGSTIN() {
		return GSTIN;
	}

	public void setGSTIN(String gSTIN) {
		GSTIN = gSTIN;
	}

	public USER_ROLE getRole() {
		return role;
	}

	public void setRole(USER_ROLE role) {
		this.role = role;
	}

	public boolean isEmailVerified() {
		return isEmailVerified;
	}

	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", sellerName=" + sellerName + ", mobile=" + mobile + ", email=" + email
				+ ", password=" + password + ", businessDetails=" + businessDetails + ", bankDetails=" + bankDetails
				+ ", pickupAddress=" + pickupAddress + ", GSTIN=" + GSTIN + ", role=" + role + ", isEmailVerified="
				+ isEmailVerified + ", accountStatus=" + accountStatus + "]";
	}
	
	
	
	
	
}

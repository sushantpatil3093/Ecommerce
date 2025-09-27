package com.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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
public class WishList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;



	@OneToOne
	private User user;
	
	@ManyToMany
	private Set<Product>product=new HashSet<>();

	public WishList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WishList(Long id, User user, Set<Product> product) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "WishList [id=" + id + ", user=" + user + ", product=" + product + "]";
	}

	
}






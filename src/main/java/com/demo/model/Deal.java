package com.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Deal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Integer discount;
	
	@OneToOne
	private HomeCategory category;

	public Deal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Deal(Long id, Integer discount, HomeCategory category) {
		super();
		this.id = id;
		this.discount = discount;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public HomeCategory getCategory() {
		return category;
	}

	public void setCategory(HomeCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Deal [id=" + id + ", discount=" + discount + ", category=" + category + "]";
	}

	
	
}

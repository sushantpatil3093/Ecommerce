package com.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class Home {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private List<HomeCategory>grid;
	
	private List<HomeCategory>shopByCategories;
	
	private List<HomeCategory>electricCategories;
	
	private List<HomeCategory>dealCategories;
	private List<Deal>deals;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<HomeCategory> getGrid() {
		return grid;
	}
	public void setGrid(List<HomeCategory> grid) {
		this.grid = grid;
	}
	public List<HomeCategory> getShopByCategories() {
		return shopByCategories;
	}
	public void setShopByCategories(List<HomeCategory> shopByCategories) {
		this.shopByCategories = shopByCategories;
	}
	public List<HomeCategory> getElectricCategories() {
		return electricCategories;
	}
	public void setElectricCategories(List<HomeCategory> electricCategories) {
		this.electricCategories = electricCategories;
	}
	public List<HomeCategory> getDealCategories() {
		return dealCategories;
	}
	public void setDealCategories(List<HomeCategory> dealCategories) {
		this.dealCategories = dealCategories;
	}
	public List<Deal> getDeals() {
		return deals;
	}
	public void setDeals(List<Deal> deals) {
		this.deals = deals;
	}
	
	
	
}

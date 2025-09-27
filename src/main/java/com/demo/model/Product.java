package com.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String title;
	
	private String description;
	
	private int mrpPrice;
	
	private int sellingPrice;
	
	private double discountPercent;
	
	private int quantity;
	
	private String color;
	
	@ElementCollection
	private List<String>image=new ArrayList<>();
	
	private int numRatings;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private Seller seller;
	
	private LocalDateTime createAt;
	
	private String size;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Review>reviews=new ArrayList<>();

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long id, String title, String description, int mrpPrice, int sellingPrice, int discountPercent,
			int quantity, String color, List<String> image, int numRatings, Category category, Seller seller,
			LocalDateTime createAt, String size, List<Review> reviews) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.mrpPrice = mrpPrice;
		this.sellingPrice = sellingPrice;
		this.discountPercent = discountPercent;
		this.quantity = quantity;
		this.color = color;
		this.image = image;
		this.numRatings = numRatings;
		this.category = category;
		this.seller = seller;
		this.createAt = createAt;
		this.size = size;
		this.reviews = reviews;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMrpPrice() {
		return mrpPrice;
	}

	public void setMrpPrice(int mrpPrice) {
		this.mrpPrice = mrpPrice;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<String> getImage() {
		return image;
	}

	public void setImage(List<String> image) {
		this.image = image;
	}

	public int getNumRatings() {
		return numRatings;
	}

	public void setNumRatings(int numRatings) {
		this.numRatings = numRatings;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", description=" + description + ", mrpPrice=" + mrpPrice
				+ ", sellingPrice=" + sellingPrice + ", discountPercent=" + discountPercent + ", quantity=" + quantity
				+ ", color=" + color + ", image=" + image + ", numRatings=" + numRatings + ", category=" + category
				+ ", seller=" + seller + ", createAt=" + createAt + ", size=" + size + ", reviews=" + reviews + "]";
	}

	
	
}

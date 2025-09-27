package com.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@Column(nullable = false)
	private String reviewText;
	
	@Column(nullable = false)
	private double rating;
	
	
	@ElementCollection
	private List<String>productImages;
	
	@JsonIgnore
	@ManyToOne
	private Product product;
	
	
	@ManyToOne
	private User user;
	
	@Column(nullable=false)
	private LocalDateTime createAt=LocalDateTime.now();

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(Long id, String reviewText, double rating, List<String> productImages, Product product, User user,
			LocalDateTime createAt) {
		super();
		this.id = id;
		this.reviewText = reviewText;
		this.rating = rating;
		this.productImages = productImages;
		this.product = product;
		this.user = user;
		this.createAt = createAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public List<String> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<String> productImages) {
		this.productImages = productImages;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", reviewText=" + reviewText + ", rating=" + rating + ", productImages="
				+ productImages + ", product=" + product + ", user=" + user + ", createAt=" + createAt + "]";
	}

	
	
}

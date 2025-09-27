package com.demo.request;

import java.util.List;

public class CreateProductRequest {

	private String title;
	private String description;
	private int mrpPrice;
	private int sellingPrice;
	private String color;
	
	private List<String>image;
	private String category;
	private String category2;
	private String category3;
	
	private String sizes;

	public CreateProductRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateProductRequest(String title, String description, int mrpPrice, int sellingPrice, String color,
			List<String> image, String category, String category2, String category3, String sizes) {
		super();
		this.title = title;
		this.description = description;
		this.mrpPrice = mrpPrice;
		this.sellingPrice = sellingPrice;
		this.color = color;
		this.image = image;
		this.category = category;
		this.category2 = category2;
		this.category3 = category3;
		this.sizes = sizes;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}

	public String getCategory3() {
		return category3;
	}

	public void setCategory3(String category3) {
		this.category3 = category3;
	}

	public String getSizes() {
		return sizes;
	}

	public void setSizes(String sizes) {
		this.sizes = sizes;
	}

	@Override
	public String toString() {
		return "CreateProductRequest [title=" + title + ", description=" + description + ", mrpPrice=" + mrpPrice
				+ ", sellingPrice=" + sellingPrice + ", color=" + color + ", image=" + image + ", category=" + category
				+ ", category2=" + category2 + ", category3=" + category3 + ", sizes=" + sizes + "]";
	}
	
	
	
}

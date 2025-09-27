package com.demo.model;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String name;
	
	@NotNull
	@Column(unique = true)
	private String categoryId;

	
	@ManyToOne
	private Category parentCategory;
	
	@NotNull
	private Integer level;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(Long id, String name, @NotNull String categoryId, Category parentCategory, @NotNull Integer level) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.parentCategory = parentCategory;
		this.level = level;
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", parentCategory="
				+ parentCategory + ", level=" + level + "]";
	}
	
	
	
}

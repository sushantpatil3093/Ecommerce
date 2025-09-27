package com.demo.service.impl;




import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.demo.exception.ProductException;
import com.demo.model.Category;
import com.demo.model.Product;
import com.demo.model.Seller;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ProductRepository;
import com.demo.request.CreateProductRequest;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Product createProduct(CreateProductRequest req, Seller seller) {
		
		Category category1=categoryRepository.findByCategoryId(req.getCategory());
		
		
		if(category1==null) {
			
		Category category=new Category();
	
	     category.setCategoryId(req.getCategory());
	     
	     category.setLevel(1);
	     category.setName(req.getCategory());
	     category1=categoryRepository.save(category);
	     
	     }
		
		Category category2=categoryRepository.findByCategoryId(req.getCategory2());
		
		if(category2==null) {
			Category category=new Category();
			category.setCategoryId(req.getCategory2());;
			category.setLevel(2);
			category.setParentCategory(category1);
			category.setName(req.getCategory2());
			category2=categoryRepository.save(category);
			
		}
		
		Category category3=categoryRepository.findByCategoryId(req.getCategory3());
		if(category3==null) {
			Category category=new Category();
			category.setCategoryId(req.getCategory3());
			category.setLevel(3);
			category.setParentCategory(category2);
			category.setName(req.getCategory3());
			category3=categoryRepository.save(category);
		}
		int discountPercentage=calculateDiscountPercentage(req.getMrpPrice(),req.getSellingPrice());
		
		Product product=new Product();
		product.setSeller(seller);
		product.setCategory(category3);
		product.setDescription(req.getDescription());
		product.setCreateAt(LocalDateTime.now());
		product.setTitle(req.getTitle());
		product.setColor(req.getColor());
		product.setSellingPrice(req.getSellingPrice());
		product.setImage(req.getImage());
		product.setMrpPrice(req.getMrpPrice());
		product.setSize(req.getSizes());
		product.setDiscountPercent(discountPercentage);
		
		return productRepository.save(product);
		
	}
	private int calculateDiscountPercentage(int mrpPrice,int sellingPrice) {
		
		if(mrpPrice<=0) {
			throw new IllegalArgumentException("Actual price must be greater then zero");
			
		}
		double discount=mrpPrice-sellingPrice;
		double discountPercentage=(discount/mrpPrice)*100;
		
		return (int)discountPercentage;
		
	}

	@Override
	public void deleteProduct(Long productId) throws ProductException{
		// TODO Auto-generated method stub
	
		Product product=findProductById(productId);
		productRepository.delete(product);
	}

	@Override
	public Product updateProduct(Long productId, Product product) throws ProductException  {
		// TODO Auto-generated method stub
		findProductById(productId);
		product.setId(productId);
		return productRepository.save(product);
		
		
	}

	@Override
	public Product findProductById(Long productId) throws ProductException{
		return productRepository.findById(productId).orElseThrow(()->
		new ProductException("product not found with id"+productId));
		
	
	}

	@Override
	public List<Product> searchProducts(String query) {
	
		return productRepository.searchProduct(query);
		
	}

	@Override
	public Page<Product> getAllProducts(String category, String brand, String colors, String sizes, String minPrice,
			String maxPrice, Integer minDisount, String sort, String stock, Integer pageNumber) {
		Specification<Product>spec=(root,query,criteriaBuilder)->{
			List<Predicate>predicates=new ArrayList<>();
			
//		if(category!=null) {
////			
////		Join<Product, Category>categoryJoin=root.join("category");
////		predicates.add(criteriaBuilder.equal(categoryJoin.get("category"),category));
////		
//			
//			Join<Product, Category> categoryJoin = root.join("category");
//			predicates.add(criteriaBuilder.equal(categoryJoin.get("category"), category));

			
			if (category != null) {
			    Join<Product, Category> categoryJoin = root.join("category");
			    predicates.add(criteriaBuilder.equal(categoryJoin.get("name"), category)); // or "categoryId"
			}

			
			if(colors !=null && !colors.isEmpty()) {
				//System.out.println("color"+colors);
				predicates.add(criteriaBuilder.equal(root.get("color"), colors));
				
			}
			if(sizes !=null && !sizes.isEmpty()) {
				predicates.add(criteriaBuilder.equal(root.get("size"), sizes));
				
			}
			if(minPrice!=null) {
				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sellingprice"),maxPrice));
				
			}
			if(maxPrice !=null) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellingprice"), maxPrice));
				
			}
			
			if(minDisount !=null) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("discountPercent"), minDisount));
				
				
			}
			if(stock!=null) {
				predicates.add(criteriaBuilder.equal(root.get("stock"), stock));
				
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
		
		Pageable pageable;

		if (sort != null && !sort.isEmpty()) {
		 pageable=  switch (sort) {
		 
		        case "price_low" ->
		            pageable = (Pageable) PageRequest.of(
		                pageNumber != null ? pageNumber : 0,
		                10,
		                Sort.by("discountprice").descending());
		           
		            
		        case "price_high" ->
		            pageable = (Pageable) PageRequest.of(
		                pageNumber != null ? pageNumber : 0,
		                10,
		                Sort.by("discountprice").ascending());
		          
		            default -> 
		           
		            pageable = (Pageable) PageRequest.of(
		                pageNumber != null ? pageNumber : 0,
		                10,
		                Sort.unsorted());
		            
		  };
		}
		else {
			pageable=(Pageable) PageRequest.of(pageNumber!=null?pageNumber:0,10,Sort.unsorted());
			
		}
	
		return productRepository.findAll(spec,pageable);
		
 
	}

	@Override
	public List<Product> getProductBySellerId(long sellerId) {
	
		return productRepository.findBySellerId(sellerId);
	}

}

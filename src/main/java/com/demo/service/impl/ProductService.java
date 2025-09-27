package com.demo.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.model.Product;
import com.demo.model.Seller;
import com.demo.request.CreateProductRequest;

public interface ProductService  {

	public Product createProduct(CreateProductRequest req,Seller seller) throws Exception;
	public void deleteProduct(Long productId) throws Exception;
	public Product updateProduct(Long productId,Product product) throws Exception;
	Product findProductById(Long productId) throws Exception;
	List<Product>searchProducts(String query) throws Exception;
   
	public Page<Product> getAllProducts(
		   String category,
		   String brand,
		   String colors,
		   String sizes,
		   String minPrice,
		   String maxPrice,
		   Integer minDisount,
		   String sort,
		   String stock,
		   Integer pageNumber) throws Exception;
   
   List<Product>getProductBySellerId(long sellerId) throws Exception;
   
}

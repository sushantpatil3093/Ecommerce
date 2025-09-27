package com.demo.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.ProductException;
import com.demo.exception.SellerException;
import com.demo.model.Product;
import com.demo.model.Seller;
import com.demo.request.CreateProductRequest;
import com.demo.service.impl.ProductService;
import com.demo.service.impl.SellerService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers/products")
public class SellerproductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private  SellerService sellerService;
	
	@GetMapping()
	public ResponseEntity<List<Product>>getProductBySellerId(
			@RequestHeader("Authorization")String jwt)throws Exception{
		
		Seller seller=sellerService.getSellerProfile(jwt);
			
			List<Product>products=productService.getProductBySellerId(seller.getId());
			return new ResponseEntity<>(products,HttpStatus.OK);
		}
	
	@PostMapping()
	 public ResponseEntity<Product>createProduct(
			 @RequestBody CreateProductRequest request,
			 @RequestHeader("Authorization")String jwt) throws Exception{
		
	System.out.println("error------"+jwt);
	Seller seller=sellerService.getSellerProfile(jwt);
	 
	    Product product=productService.createProduct(request, seller);
   //   Product product=new Product();
      
	return new ResponseEntity<>(product,HttpStatus.CREATED) ;
	     

}
      @DeleteMapping("/{productId}")
      public ResponseEntity<Product>deleteProduct(@PathVariable Long productId) throws Exception{
    	  
    	  try {
    		  productService.deleteProduct(productId);
    		  return new ResponseEntity<>(HttpStatus.OK);
    	  }catch(ProductException e) {
    		  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		  
      
      
      
    	  }
      }}


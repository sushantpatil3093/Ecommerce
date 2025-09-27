package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Cart;
import com.demo.model.CartItem;
import com.demo.model.Product;
import com.demo.model.User;
import com.demo.request.AddItemRequest;
import com.demo.response.ApiResponse;
import com.demo.service.impl.CartItemService;
import com.demo.service.impl.CartService;
import com.demo.service.impl.ProductService;
import com.demo.service.impl.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private  CartService cartService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productServcie;
	
	
	@GetMapping
	public ResponseEntity<Cart>findUserCartHandler(
			@RequestHeader("Authorization")String jwt)throws Exception{
		
		User user=userService.findUserByJwtToken(jwt);
		
		Cart cart=cartService.findUserCart(user);
		System.out.println("cart "+cart.getUser().getEmail());
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
		
	}
	  
	@PutMapping("/add")
	 public ResponseEntity<CartItem>addItemTocart(@RequestBody AddItemRequest req,
			 @RequestHeader("Authorization")String jwt) throws Exception {
		
		User user=userService.findUserByJwtToken(jwt);
		Product product=productServcie.findProductById(req.getProductId());
		
		CartItem item=cartService.addCartItem(user, product, req.getSize(), req.getQuantity());
		
		ApiResponse res=new ApiResponse();
		res.setMessage("item added to cart successfully");
		
		
		return new ResponseEntity<>(item,HttpStatus.ACCEPTED);
	}
	
	    @DeleteMapping("/item/{cartItemId}")
        public ResponseEntity<ApiResponse>deleteCartitemHandler(
        		@PathVariable Long cartItemId,
        		@RequestHeader ("Authorization")String jwt)throws Exception{
        	
	    	System.out.println("Received JWT: " + jwt);

        	//User user=userService.findUserByJwtToken(jwt);

           // String token = jwt.startsWith("Bearer ") ? jwt.substring(7) : jwt;
            User user=userService.findUserByJwtToken(jwt);
            
//            cartItemService.removeCartItem(user.getId(),cartItemId);
//        	
      	ApiResponse res=new ApiResponse("Item Remove from cart");
//        	
            
        	return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	    }
	    
	    @PutMapping("/item/{cartItemId}")
	    public ResponseEntity<CartItem>updateCartItemHandler(
	    		@PathVariable Long cartItemId,
	    		@RequestBody CartItem cartItem,
	    		@RequestHeader("Authorization")String jwt) throws Exception {
		
	    User user=userService.findUserByJwtToken(jwt);
	    
	    CartItem updateCartItem=null;
	    if(cartItem.getQuantity()>0) {
	    	updateCartItem=cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
	    	
	    }
	    return new ResponseEntity<>(updateCartItem,HttpStatus.ACCEPTED);
	    
}
}


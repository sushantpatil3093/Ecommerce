package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.config.JwtProvider;
import com.demo.domain.AccountStatus;
import com.demo.exception.SellerException;
import com.demo.model.Seller;
import com.demo.model.SellerReport;
import com.demo.model.VerificationCode;
import com.demo.repository.SellerRepository;
import com.demo.repository.VerificationCodeRepository;
import com.demo.request.LoginOtpRequest;
import com.demo.request.LoginRequest;
import com.demo.response.ApiResponse;
import com.demo.response.AuthResponse;
import com.demo.service.impl.AuthService;
import com.demo.service.impl.EmailService;
import com.demo.service.impl.SellerReportService;
import com.demo.service.impl.SellerService;
import com.demo.utils.OtpUtil;

import jakarta.mail.MessagingException;
import jakarta.validation.constraints.AssertFalse.List;
import lombok.RequiredArgsConstructor;




@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {

      @Autowired 	
	  private  SellerService sellerService;
	  
      @Autowired
      private VerificationCodeRepository verificationCodeRepository;
      
      @Autowired
      private AuthService authService;
   
      @Autowired
      private EmailService emailService;
      
      @Autowired
      private SellerReportService sellerReportService;
      
      
//        @PostMapping("/sent/login-otp") 
//    	public ResponseEntity<ApiResponse>sentOtpHandler(@RequestBody LoginOtpRequest  req) throws Exception{
//  		
//  		authService.sentLoginOtp(req.getEmail(),req.getRole());
//  		
//  	ApiResponse res=new ApiResponse();
//  	
//  	res.setMessage("otp sent successfully");
//  		
//  		return ResponseEntity.ok(res);
//  	}
      
       @PostMapping("/login")
       public ResponseEntity<AuthResponse>loginSeller(@RequestBody LoginRequest req) throws Exception{
	  
	  String otp=req.getOtp();
	  String email=req.getEmail();
	  
	 
       
//	  
	  req.setEmail("seller_"+email);
	  System.out.println(otp +" "+email);
	  AuthResponse authResponse=authService.signing(req);
	  
	  return ResponseEntity.ok(authResponse);
  }
      @PatchMapping("/verify/{otp}")
      public ResponseEntity<Seller>verifySellerEmail(@PathVariable String otp) throws Exception{
    	  
    	  VerificationCode verificationCode=verificationCodeRepository.findByOtp(otp);
    	  
    	  if(verificationCode==null || !verificationCode.getOtp().equals(otp)) {
    		  throw new Exception("wrong otp");
    	  
    	  } 
    	  
    	  Seller seller=sellerService.verifyEmail(verificationCode.getEmail(), otp);
    	  return new ResponseEntity<>(seller,HttpStatus.OK);
      
    	  
      }
        @PostMapping
        public ResponseEntity<Seller>createSeller(
    		   @RequestBody Seller seller)throws Exception,MessagingException{
    	   Seller savedSeller=sellerService.createSeller(seller);
    	   
    	   String otp=OtpUtil.generateOtp();
    	   
    	   VerificationCode verificationCode=new VerificationCode();
    	   verificationCode.setOtp(otp);
    	   verificationCode.setEmail(seller.getEmail());
    	   verificationCodeRepository.save(verificationCode);
    	   
    	   String subject = "sushant here verification Code";
    	   String text="Welcome to zosh bazzar , verify your account using this link";
    	   
    	   String frontend_url = "http://localhost:3000/verify-seller/";
    	   
    	   emailService.sendVerificationEmail(seller.getEmail(), verificationCode.getOtp(), subject, text+frontend_url);
              return new ResponseEntity<>(savedSeller,HttpStatus.CREATED);
        }
       
       @GetMapping("/{id}")
       public ResponseEntity<Seller>getSellerById(@PathVariable Long id) throws SellerException{
    	   Seller seller=sellerService.getSellerById(id);
    	   return new ResponseEntity<>(seller,HttpStatus.OK);
    	   
       }
         
         @GetMapping("/profile")
         public ResponseEntity<Seller>getSellerByJwt(
        		 @RequestHeader("Authorization")String jwt) throws Exception{
        
        	 Seller seller=sellerService.getSellerProfile(jwt);
        	 return new ResponseEntity<>(seller,HttpStatus.OK);
        	 
         }
         @GetMapping("/report")
         public ResponseEntity<SellerReport>getSellerReport(
        		 @RequestHeader("Authorization")String jwt) throws Exception{
        	 
        	 Seller seller=sellerService.getSellerProfile(jwt);
        	 SellerReport report=sellerReportService.getSelleReport(seller);
        	 return new ResponseEntity<>(report,HttpStatus.OK);
        	 
         }
         
           @GetMapping
           public ResponseEntity<java.util.List<Seller>>getAllSellers(
        		   @RequestParam(required = false)AccountStatus status) throws Exception{
        	   java.util.List<Seller>sellers=sellerService.getAllSeller(status);
        	   return ResponseEntity.ok(sellers);
           }
      
    
            @PatchMapping()
            public ResponseEntity<Seller>updateSeller(
            		@RequestHeader("Authorization")String jwt,
            		@RequestBody Seller seller)throws Exception {
        	   
        	   Seller profile=sellerService.getSellerProfile(jwt);
        	   Seller updateSeller=sellerService.updateSeller(profile.getId(), seller);
        	   return ResponseEntity.ok(updateSeller);
           }
           
           @DeleteMapping("/id")
           public ResponseEntity<Void>deleteSeller(@PathVariable Long id) throws Exception{
        	   
        	   sellerService.deleteSeller(id);
        	   return ResponseEntity.noContent().build();
           }
           
}

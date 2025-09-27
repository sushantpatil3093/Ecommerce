package com.demo.service.impl;

import java.util.ArrayList;




import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthoritiesAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.config.JwtProvider;
import com.demo.domain.USER_ROLE;
import com.demo.model.Cart;
import com.demo.model.Seller;
import com.demo.model.User;
import com.demo.model.VerificationCode;
import com.demo.repository.CartRepository;
import com.demo.repository.SellerRepository;
import com.demo.repository.UserRepository;
import com.demo.repository.VerificationCodeRepository;
import com.demo.request.LoginRequest;
import com.demo.response.AuthResponse;
import com.demo.response.SignupRequest;
import com.demo.utils.OtpUtil;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{



            @Autowired
             private UserRepository userRepository;

           @Autowired
           private  PasswordEncoder passwordEncoder;

       @Autowired
       private  CartRepository cartRepository;

        @Autowired
         private  JwtProvider jwtProvider;

              @Autowired
              private VerificationCodeRepository verificationCodeRepository;

             @Autowired 
             private SellerRepository sellerRepository;      
             
             @Autowired 
             private  EmailService emailService;
            
             @Autowired
             private CustomUserServiceImpl customerService;
	
	
             
    @Override
	public void sentLoginOtp(String email,USER_ROLE role) throws Exception {
	
    	
	String SIGNING_PREFIX="signing_";

	
	 if(email.startsWith(SIGNING_PREFIX)) {
		 email=email.substring(SIGNING_PREFIX.length());
		 
		 if(role.equals(USER_ROLE.ROLE_SELLER)) {
		
	      Seller seller=sellerRepository.findByEmail(email);
	      
			  if(seller==null) {
				  throw new Exception("seller not found");
			  }
		 }
		 
	 } else {
		    System.out.println("email"+email);
			 User user=userRepository.findByEmail(email);
			 
			 if(user==null) {
				 throw new Exception("email not exist with provided email");
			 }
	   }
		   
	 
	VerificationCode isExist=verificationCodeRepository.findByEmail(email);
	 if(isExist!=null) {
		 verificationCodeRepository.deleteAll();
	 }
     String otp=OtpUtil.generateOtp();
     
     VerificationCode verificationCode=new VerificationCode();
     verificationCode.setOtp(otp);
     verificationCode.setEmail(email);
     
     verificationCodeRepository.save(verificationCode);
     
     String subject="zosh bazzar login/signup otp ";
     String text= "your login/signup otp :" + otp;
	
      emailService.sendVerificationEmail(email, otp, subject, text);
	}

	@Override
	public String createUser(SignupRequest req) throws Exception {
		// TODO Auto-generated method stub
		
	
		VerificationCode verificationCode=verificationCodeRepository.findByEmail(req.getEmail());
		if(verificationCode==null || !((VerificationCode) verificationCode).getOtp().equals(req.getOtp())) {
			throw new Exception("wrong otp..");
			
		}
		
		User user=userRepository.findByEmail(req.getEmail());
		if(user==null) {
			User createUser=new User();
			createUser.setEmail(req.getEmail());
			createUser.setFullName(req.getFullName());
			createUser.setRole(USER_ROLE.ROLE_CUSTOMER);
			createUser.setMobile("9877464699");
			createUser.setPassword(passwordEncoder.encode(req.getOtp()));
			
		user=userRepository.save(createUser);
		
		Cart cart=new Cart();
		cart.setUser(user);
		
		cartRepository.save(cart);
		
		}
		
		List<GrantedAuthority>authorities=new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(req.getEmail(), null,authorities);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		return jwtProvider.generateToken(authentication);
	}

	@Override
	public AuthResponse signing(LoginRequest req) throws Exception {
		
		
		String username=req.getEmail();
		String otp=req.getOtp();
		
		Authentication authentication = authenticate (username,otp);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("login success");
		
		Collection<? extends GrantedAuthority>authorities=authentication.getAuthorities();
		String roleName=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
		
		authResponse.setRole(USER_ROLE.valueOf(roleName));
		
			return authResponse;
		}
		private Authentication authenticate(String username,String otp) throws Exception {
			
		UserDetails userDetails=customerService.loadUserByUsername(username);
		
		
		String SELLER_PREFIX="seller_";
		
          if(username.startsWith(SELLER_PREFIX)) {
		
        	  username=username.substring(SELLER_PREFIX.length());
			
		}
				
		if(userDetails==null) {
			throw new BadCredentialsException("invalid username");
		}
		
		 VerificationCode verificationCode=verificationCodeRepository.findByEmail(username);
		  
		  if(verificationCode==null || 	!((VerificationCode) verificationCode).getOtp().equals(otp)) {
			  throw new Exception("wrong otp");
			  
		  }
		
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		
	}


		}

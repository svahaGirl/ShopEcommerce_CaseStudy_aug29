package com.shop.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shop.admin.user.UserRepository;
import com.shop.common.entity.User;


//add superclass when create class
public class ShopUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=userRepo.getUserByEmail(email);
		if(user != null) {
			return new ShopUserDetails(user);
		}
		throw new UsernameNotFoundException("Could not find user with this email: " + email);
	}

}

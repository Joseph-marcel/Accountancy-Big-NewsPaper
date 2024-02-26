package com.ogivesas.journal.servicesImpl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ogivesas.journal.configuration.userDetailsConfig.AppUser;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService{

	private CustomUserDetailServiceImpl   appRoleServiceImpl;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		 AppUser appUser = appRoleServiceImpl.loadUserByUsername(username);
		if(appUser == null) throw new UsernameNotFoundException(String.format("User %s not found", username));
		
		String[] roles = appUser.getRoles().stream().map(u -> u.getRole()).toArray(String[]::new);
		UserDetails userDetails = User
				.withUsername(appUser.getUsername())
			    .password(appUser.getPassword())
			    .roles(roles)
			    .build();
		
		return userDetails;
	}

}

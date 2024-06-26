package com.ogivesas.journal.services;

import java.util.List;
import com.ogivesas.journal.configuration.userDetailsConfig.AppRole;
import com.ogivesas.journal.configuration.userDetailsConfig.AppUser;

public interface CustomUserDetailService {

	AppUser addNewUser(String username,String password,String email,String confirmPassword);
	AppUser updateUser(AppUser appUser);
	AppRole addNewRole(String role);
	void    addRoleToUser(String username,String role);
	void removeRoleFromUser(String username,String role);
	AppUser loadUserByUsername(String username);
	List<AppRole> listRoles();
	List<AppUser> listUsers();
	
}

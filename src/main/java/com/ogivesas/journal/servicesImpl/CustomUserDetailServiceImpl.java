package com.ogivesas.journal.servicesImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogivesas.journal.configuration.userDetailsConfig.AppRole;
import com.ogivesas.journal.configuration.userDetailsConfig.AppUser;
import com.ogivesas.journal.repositories.AppRoleRepository;
import com.ogivesas.journal.repositories.AppUserRepository;
import com.ogivesas.journal.services.CustomUserDetailService;

import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class CustomUserDetailServiceImpl implements CustomUserDetailService{
	
	private AppUserRepository   appUserRepo;
	private AppRoleRepository   appRoleRepo;
	private PasswordEncoder     passwordEncoder;
	
	
	
	@Override
	public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
		// TODO Auto-generated method stub
		
		AppUser appUser = loadUserByUsername(username);
		if(appUser != null) throw new RuntimeException("User already exist");
		if(!password.equals(confirmPassword)) throw new RuntimeException("Password does not match");
		
		 appUser = AppUser.builder()
		        .userId(UUID.randomUUID().toString())
		        .username(username)
		        .password(passwordEncoder.encode(password))
		        .email(email)
		        .active(true)
		        .build();
		 appUserRepo.save(appUser);
		 
		 return appUser;
	}

	@Override
	public AppRole addNewRole(String role) {
		// TODO Auto-generated method stub
		
		AppRole appRole = (AppRole) appRoleRepo.findById(role).orElse(null);
		if(appRole!= null) throw new RuntimeException("Role already exist");
		appRole = AppRole.builder()
				         .role(role)
				         .build();
		 
		
		return appRoleRepo.save(appRole);
	}

	@Override
	public void addRoleToUser(String username, String role) {
		// TODO Auto-generated method stub
		
		AppUser appUser = loadUserByUsername(username);
		AppRole appRole = appRoleRepo.findById(role).orElse(null);
		
		appUser.getRoles().add(appRole);
	}

	@Override
	public void removeRoleFromUser(String username, String role) {
		// TODO Auto-generated method stub
		
		AppUser appUser = loadUserByUsername(username);
		AppRole appRole = appRoleRepo.findById(role).get();
		
		appUser.getRoles().remove(appRole);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		
		return appUserRepo.findByUsername(username);
	}

	@Override
	public List<AppRole> listRoles() {
		// TODO Auto-generated method stub
		return appRoleRepo.findAll();
	}

}

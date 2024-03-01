package com.ogivesas.journal.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.ogivesas.journal.configuration.userDetailsConfig.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String>{

	AppUser findByUsername(String username);
	
}

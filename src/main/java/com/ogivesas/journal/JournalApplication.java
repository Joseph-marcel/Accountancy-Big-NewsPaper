package com.ogivesas.journal;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ogivesas.journal.configuration.userDetailsConfig.AppRole;
import com.ogivesas.journal.configuration.userDetailsConfig.AppUser;
import com.ogivesas.journal.services.CustomUserDetailService;
import com.ogivesas.journal.services.JournalService;

import lombok.AllArgsConstructor;



@SpringBootApplication
@AllArgsConstructor
public class JournalApplication implements CommandLineRunner{

	private JournalService iJournalService;
	private CustomUserDetailService  customUser;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub		
		
		
		
		/*
		 * Customer cstm = Director.customerBuilder() .name("OGIVE SAS")
		 * .email("siogivesas@gmail.com") .taxPayerNumber("M091612571014S")
		 * .phoneNumber("(+237) 694 674 286 / 694467 982") .allowances() .build();
		 * 
		 * iJournalService.addCustomer(cstm);
		 */
		 
		/*
		 * AppUser appUser = customUser.addNewUser("zambio", "1234",
		 * "zambio13@gmail.com", "1234");
		 */
		
		/*
		 * AppRole appRole = customUser.addNewRole("SUPER");
		 * customUser.addRoleToUser("zambio", "SUPER");
		 */
		/*
		 * AppRole accountant = customUser.addNewRole("ACCOUNTANT");
		 * customUser.addRoleToUser("zambio", "ACCOUNTANT");
		 */
	}

	
}

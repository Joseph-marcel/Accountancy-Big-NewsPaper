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

	@SuppressWarnings("unused")
	private JournalService iJournalService;
	
	@SuppressWarnings("unused")
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
		 * AppUser appUser =
		 * customUser.addNewUser("siogive","201710","siogivesas@gmail.com", "201710");
		 * 
		 * 
		 * customUser.addRoleToUser("siogive", "SUPER");
		 */
		 
		 
		 
	}

	
}

package com.ogivesas.journal;


import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ogivesas.journal.models.Customer;
import com.ogivesas.journal.models.Director;
import com.ogivesas.journal.services.JournalService;

import lombok.AllArgsConstructor;



@SpringBootApplication
@AllArgsConstructor
public class JournalApplication implements CommandLineRunner{

	private JournalService iJournalService;
	
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
		 
		 
	}

	
}

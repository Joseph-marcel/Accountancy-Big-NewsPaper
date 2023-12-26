package com.ogivesas.journal;




import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

import com.ogivesas.journal.models.Allowance;
import com.ogivesas.journal.models.Contractor;
import com.ogivesas.journal.models.Customer;
import com.ogivesas.journal.models.Director;
import com.ogivesas.journal.servicesImpl.JournalServiceImpl;

import lombok.AllArgsConstructor;
import utils.JsonSerializer;

@SpringBootApplication
@AllArgsConstructor
public class JournalApplication implements CommandLineRunner{
	
	private JournalServiceImpl journalS;

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		JsonSerializer<Customer> customerJsonSerializer = new JsonSerializer<>();
		JsonSerializer<Allowance> allowanceJsonSerializer = new JsonSerializer<>();
		JsonSerializer<Contractor> contractorJsonSerializer = new JsonSerializer<>();
		
		/*
		 * Customer cstm = journalS.initialCustomer();
		 * 
		 * 
		 * Allowance allowance = Director.allowanceBuilder()
		 * .allowanceName("Electricité") .customer(cstm) .invoices() .build();
		 * journalS.addAllowance(allowance);
		 */
		
		
		Contractor company = Director.contractorBuilder()
				.name("DOVV Distribution")
				.email("dovv@yahoo.fr")
				.taxPayNumber("M0123588497S")
				.invoices()
				.build();
		journalS.addContractor(company);
		
		
		
		
		/*
		 * Page<Contractor> lists = journalS.listContractors("CONTRACTOR", 1, 5);
		 * lists.stream().map(contractorJsonSerializer.toJson(company))
		 * .forEach(System.out.println());
		 */
		
		System.out.println("***************************");
		/*
		 * System.out.println(allowanceJsonSerializer.toJson(allowance));
		 * System.out.println(customerJsonSerializer.toJson(cstm));
		 */
		System.out.println(contractorJsonSerializer.toJson(company));
		System.out.println("***************************");
		
	}

	

}

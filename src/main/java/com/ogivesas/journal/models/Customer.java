package com.ogivesas.journal.models;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Entity
@DiscriminatorValue("CUSTOMER")
@Data @NoArgsConstructor  @AllArgsConstructor 
@ToString
@EqualsAndHashCode(callSuper=false)
public class Customer extends Company{
   
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	@JsonBackReference
	private List<Allowance> allowances;
	
	
	public static class CompanyBuilder{
		
		private Customer customer = new Customer();
		
		public CompanyBuilder companyId(Long id) {
			   customer.setCompanyId(id);
			   
			return this;
		}
		
		public CompanyBuilder name(String name) {
			customer.setName(name);
			
			return this;
		}
		
		public CompanyBuilder taxPayerNumber(String taxPayerNumber) {
			customer.setTaxPayerNumber(taxPayerNumber);
			
			return this;
		}
		
		public CompanyBuilder email(String email) {
			customer.setEmail(email);
			
			return this;
		}
		
		public CompanyBuilder phoneNumber(String phoneNumber) {
			customer.setPhoneNumber(phoneNumber);
			
			return this;
		}
		
		public CompanyBuilder allowances() {
			customer.allowances = new ArrayList<Allowance>();
			
			return this;
		}
		
		public Customer build() {
			return this.customer;
		}
	}
	       
}

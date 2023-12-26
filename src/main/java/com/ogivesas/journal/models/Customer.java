package com.ogivesas.journal.models;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@DiscriminatorValue("CUSTOMER")
@Data @NoArgsConstructor  @AllArgsConstructor 
@ToString
@EqualsAndHashCode(callSuper=false)
public class Customer extends Company{
   
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Allowance> allowances;
	
	
	public static class CompanyBuilder{
		
		private Customer customer = new Customer();
		
		public CompanyBuilder companyId(Long id) {
			   customer.companyId =id;
			   
			return this;
		}
		
		public CompanyBuilder name(String name) {
			customer.name = name;
			
			return this;
		}
		
		public CompanyBuilder taxPayNumber(String taxPayNumber) {
			customer.taxPayerNumber = taxPayNumber;
			
			return this;
		}
		
		public CompanyBuilder email(String email) {
			customer.email = email;
			
			return this;
		}
		
		public CompanyBuilder phoneNumber(String phoneNumber) {
			customer.phoneNumber = phoneNumber;
			
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

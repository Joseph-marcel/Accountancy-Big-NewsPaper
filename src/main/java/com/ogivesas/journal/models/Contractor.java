package com.ogivesas.journal.models;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data  @NoArgsConstructor @AllArgsConstructor @Builder
@DiscriminatorValue("CONTRACT")
@EqualsAndHashCode(callSuper= false)
public class Contractor extends Company{

	@OneToMany(mappedBy = "contractor", fetch = FetchType.LAZY)
	private List<Invoice> invoices;
	
	
	public static class CompanyBuilder{
		
		private Contractor contractor = new Contractor();
		
		public CompanyBuilder companyId(Long id) {
			contractor.companyId =id;
			   
			return this;
		}
		
		public CompanyBuilder name(String name) {
			contractor.name = name;
			
			return this;
		}
		
		public CompanyBuilder taxPayNumber(String taxPayNumber) {
			contractor.taxPayerNumber = taxPayNumber;
			
			return this;
		}
		
		public CompanyBuilder email(String email) {
			contractor.email = email;
			
			return this;
		}
		
		public CompanyBuilder phoneNumber(String phoneNumber) {
			contractor.phoneNumber = phoneNumber;
			
			return this;
		}
		
		public CompanyBuilder invoices() {
			contractor.invoices = new ArrayList<Invoice>();
			
			return this;
		}
		
		public Contractor build() {
			
			return this.contractor;
		}
	}
}

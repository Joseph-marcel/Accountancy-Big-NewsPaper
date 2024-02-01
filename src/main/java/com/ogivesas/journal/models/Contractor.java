package com.ogivesas.journal.models;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

	@OneToMany(mappedBy = "contractor", fetch = FetchType.EAGER)
	@JsonBackReference
	private List<Invoice> invoices;
	
	
	public static class CompanyBuilder{
		
		private Contractor contractor = new Contractor();
		
		public CompanyBuilder companyId(Long id) {
			contractor.setCompanyId(id);
			   
			return this;
		}
		
		public CompanyBuilder name(String name) {
			contractor.setName(name);
			
			return this;
		}
		
		public CompanyBuilder taxPayerNumber(String taxPayerNumber) {
			contractor.setTaxPayerNumber(taxPayerNumber);
			
			return this;
		}
		
		public CompanyBuilder email(String email) {
			contractor.setEmail(email);
			
			return this;
		}
		
		public CompanyBuilder phoneNumber(String phoneNumber) {
			contractor.setPhoneNumber(phoneNumber);
			
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

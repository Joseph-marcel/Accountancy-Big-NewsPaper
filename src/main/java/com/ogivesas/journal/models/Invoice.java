package com.ogivesas.journal.models;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data  @NoArgsConstructor @AllArgsConstructor @Builder 
public class Invoice {

	
	@Id
	@Column(name = "invoice_id")
	private String invoiceId;
	@Column(name = "invoice_number", nullable = false)
	@NotEmpty(message = "Champ obligatoire")
	private String invoiceNumber;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;
	@Column(nullable = false)
	@NotNull(message = "Champ obligatire")
	private Integer amount;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@Valid 
	@Builder.Default
	private Contractor contractor = new Contractor();
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@Valid 
	@Builder.Default
	private Allowance  allowance = new Allowance();

	
	
	public static class InvoiceBuilder{
		
		private Invoice invoice = new Invoice();
		
		public InvoiceBuilder invoiceId(String invoiceId) {
			
			invoice.invoiceId = invoiceId;
			return this;
		}
		
		public InvoiceBuilder invoiceNumber(String invoiceNumber) {
			
			invoice.invoiceNumber = invoiceNumber;
			return this;
		}
		
		public InvoiceBuilder  createAt(Date createAt) {
			
			invoice. createAt =  createAt;
			return this;
		}
		
		public InvoiceBuilder amount(Integer amount) {
			
			invoice.amount = amount;
			return this;
		}
		
		
		  public InvoiceBuilder contractor(Contractor contractor) {
		  
			  invoice.contractor = contractor; 
			  return this; 
		  }
		  
		  public InvoiceBuilder allowance(Allowance allowance) {
		  
			  invoice.allowance = allowance;
			  return this; 
		  }
		 
		
		public Invoice build() {
			
			return this.invoice;
		}

		
	}

}

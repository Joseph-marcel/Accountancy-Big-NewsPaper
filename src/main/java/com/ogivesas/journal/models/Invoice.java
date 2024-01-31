package com.ogivesas.journal.models;



import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
	private String invoiceNumber;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	@Column(nullable = false)
	private Integer amount;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Contractor contractor;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Allowance  allowance;
	
	
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
		
		public InvoiceBuilder date(Date date) {
			
			invoice.date = date;
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

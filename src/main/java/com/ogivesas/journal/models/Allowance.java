package com.ogivesas.journal.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@ToString
public class Allowance {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="allowance_id")
	private Long allowanceId;
	@Column(name="allowance_name",nullable = false)
	private String allowanceName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Invoice> invoices;
	
	
	public static class AllowanceBuilder{
		
		private Allowance allowance = new Allowance();
		
		
		public AllowanceBuilder allowanceId(Long id) {
			
			allowance.allowanceId = id;
			return this;
		}
		
		public AllowanceBuilder allowanceName(String name) {
			
			allowance.allowanceName = name;
			return this;
		}
		
		public AllowanceBuilder customer(Customer customer) {
			
			allowance.customer = customer;
			return this;
		}
		
		public AllowanceBuilder invoices() {
			
			allowance.invoices = new ArrayList<Invoice>();
			return this;
		}
		
		public Allowance build() {
			
			return this.allowance;
		}
	}
}

package com.ogivesas.journal.models;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Allowance {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="allowance_id")
	private Long allowanceId;
	@Column(name="allowance_name",nullable = false)
	@NotNull
	private String allowanceName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	private Customer customer;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE,orphanRemoval = false)
	@JsonBackReference
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
		
		public AllowanceBuilder customer(Customer ogivesas) {
			
			allowance.customer = ogivesas;
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

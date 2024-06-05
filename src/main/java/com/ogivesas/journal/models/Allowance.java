package com.ogivesas.journal.models;

import java.util.*;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


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

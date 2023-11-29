package com.ogivesas.journal.models;

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
@DiscriminatorValue("CUSTOMER")
@Data @NoArgsConstructor  @AllArgsConstructor @Builder
@EqualsAndHashCode(callSuper = false)
public class Customer extends Company{
   
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Allowance> allowances;

	
}

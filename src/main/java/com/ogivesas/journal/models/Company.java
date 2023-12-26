package com.ogivesas.journal.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 8)
public abstract class Company {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="company_id")
	protected Long companyId;
	@Column(unique = true, nullable = false)
	protected String name;
	@Column(name="tax_payer_number", unique = true)
	protected String taxPayerNumber;
	protected String email;
	@Column(name="phone_number", unique = true)
	protected String phoneNumber;
	
}

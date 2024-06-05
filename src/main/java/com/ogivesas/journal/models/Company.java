package com.ogivesas.journal.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",discriminatorType = DiscriminatorType.STRING ,length = 8)
public abstract class Company {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="company_id")
	private Long companyId;
	@Column(unique = true, nullable = false)
	private String name;
	@Column(name="tax_payer_number")
	private String taxPayerNumber;
	private String email;
	@Column(name="phone_number")
	private String phoneNumber;
	
}

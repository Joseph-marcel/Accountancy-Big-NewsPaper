package com.ogivesas.journal.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
	@Column(name = "invoice_number")
	private String invoiceNumber;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	@Column(nullable = false)
	private Integer amount;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Contractor contractor;
	@ManyToOne(fetch = FetchType.EAGER)
	private Allowance  allowance;
}

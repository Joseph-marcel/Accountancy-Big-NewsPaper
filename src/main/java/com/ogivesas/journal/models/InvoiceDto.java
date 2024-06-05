package com.ogivesas.journal.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceDto {

	
	@Column(name = "invoice_number", nullable = false)
	@NotEmpty(message = "Champ obligatoire")
	private String invoiceNumber;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;
	@Column(nullable = false)
	@NotNull(message = "Champ obligatire")
	private Integer amount;
	private MultipartFile imageFile;
	
	
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
	
	
}

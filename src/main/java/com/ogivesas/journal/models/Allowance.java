package com.ogivesas.journal.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Allowance {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="allowance_id")
	private Long allowanceId;
	@Column(name="allowance_name",nullable = false)
	private String allowanceName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
}

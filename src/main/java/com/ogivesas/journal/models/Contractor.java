package com.ogivesas.journal.models;

import java.util.List;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data  @DiscriminatorValue("CONTRACT")
@EqualsAndHashCode(callSuper= false)
@Builder
public class Contractor extends Company{

	@OneToMany(mappedBy = "contractor", fetch = FetchType.LAZY)
	private List<Invoice> invoices;
}

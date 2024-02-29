package com.ogivesas.journal.configuration.userDetailsConfig;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AppRole {

	@Id
	@NotEmpty(message = "Entrer un nom pour le role")
	private String role;
}

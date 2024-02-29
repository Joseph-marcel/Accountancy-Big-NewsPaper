package com.ogivesas.journal.configuration.userDetailsConfig;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AppUser {

	@Id
	private String userId;
	@NotEmpty(message = "Entrer un nom d'utilisateur")
	@NotNull(message = "Au moins une lettre alphabétique")
	private String username;
	@Email
	@Column(unique = true)
	private String email;
	@NotEmpty(message = "Champ obligatoire")
	private String password;
	@NotEmpty(message = "Champ obligatoire")
	private String confirmPassword;
	private Boolean active;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<AppRole> roles;
}

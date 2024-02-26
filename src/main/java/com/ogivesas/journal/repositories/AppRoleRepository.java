package com.ogivesas.journal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ogivesas.journal.configuration.userDetailsConfig.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, String>{

}

package com.ogivesas.journal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ogivesas.journal.models.Company;


public interface CompanyRepository extends JpaRepository<Company, Long>{

}

package com.ogivesas.journal.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ogivesas.journal.models.Company;
import com.ogivesas.journal.models.Contractor;


public interface CompanyRepository extends JpaRepository<Company, Long>{

	@Query(value="SELECT * FROM Company c WHERE c.type = :type ORDER BY c.name ASC", nativeQuery = true)
	public Page<Contractor> listContractors(@Param("type") String type,PageRequest pageRequest);

	public Company findByName(String name);

}

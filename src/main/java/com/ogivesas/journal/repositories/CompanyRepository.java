package com.ogivesas.journal.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ogivesas.journal.models.Company;
import com.ogivesas.journal.models.Contractor;


public interface CompanyRepository extends JpaRepository<Company, Long>{

	@Query(value="SELECT * FROM Company c WHERE c.type = :type ORDER BY c.name ASC", nativeQuery = true)
    Page<Contractor> listContractors(@Param("type") String type,PageRequest pageRequest);
	@Query(value="SELECT * FROM Company c WHERE c.type = :type ORDER BY c.name ASC", nativeQuery = true)
	List<Contractor> listTenders(@Param("type") String type);
	Company findByName(String name);

}

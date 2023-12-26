package com.ogivesas.journal.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ogivesas.journal.models.Company;
import com.ogivesas.journal.models.Contractor;


public interface CompanyRepository extends JpaRepository<Company, Long>{

	@Query(value="SELECT c.name FROM Contractor c WHERE c.type = :type ORDER BY c.name DESC", nativeQuery = true)
	public Page<Contractor> listContractors(@Param("type") String type,PageRequest pageRequest);
	
	public Contractor findByName(String name);
}

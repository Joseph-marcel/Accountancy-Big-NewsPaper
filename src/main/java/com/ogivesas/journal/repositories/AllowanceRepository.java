package com.ogivesas.journal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ogivesas.journal.models.Allowance;


public interface AllowanceRepository extends JpaRepository<Allowance, Long>{

	 Allowance findByAllowanceName(String name);
	 List<Allowance> findAllByOrderByAllowanceNameAsc();
}

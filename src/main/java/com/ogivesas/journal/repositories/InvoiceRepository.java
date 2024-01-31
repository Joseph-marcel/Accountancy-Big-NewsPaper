package com.ogivesas.journal.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ogivesas.journal.models.Invoice;


public interface InvoiceRepository extends JpaRepository<Invoice, String>{
	
	@Query(value="SELECT * FROM Invoice i  ORDER BY i.create_at DESC", nativeQuery = true)
	public Page<Invoice> listInvoices(PageRequest pageRequest);
	
	@Query(value="SELECT * FROM Invoice i  WHERE i.create_at = :createAt ORDER BY i.create_at DESC", nativeQuery = true)
	public Page<Invoice> listInvoicesPerSearchDate(@Param("createAt") Date createAt,  PageRequest pageRequest);
	
	public Invoice findByInvoiceId(String id);
}

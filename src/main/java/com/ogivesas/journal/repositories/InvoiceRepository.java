package com.ogivesas.journal.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ogivesas.journal.models.Invoice;


public interface InvoiceRepository extends JpaRepository<Invoice, String>{
	
	@Query(value = "SELECT * FROM Invoice i  ORDER BY i.create_at DESC", nativeQuery = true)
	public Page<Invoice> listInvoices(PageRequest pageRequest);
	
	@Query(value = "SELECT * FROM Invoice i  WHERE i.create_at = :createAt ORDER BY i.create_at DESC", nativeQuery = true)
	public Page<Invoice> listInvoicesPerSearchDate(@Param("createAt") Date createAt,  PageRequest pageRequest);
	
	@Query(value = "SELECT * FROM Invoice i WHERE i.create_at BETWEEN :startDate AND :endDate  ORDER BY i.create_at DESC", nativeQuery = true)
	public Page<Invoice> listInvoicesPerMonth(@Param("startDate") Date startDate, @Param("endDate") Date endDate, PageRequest pageRequest);
	
	@Query(value = "SELECT * FROM Invoice i WHERE i.create_at BETWEEN :startDate AND :endDate ", nativeQuery = true)
	public List<Invoice> invoicesPerMonth(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	public Invoice findByInvoiceNumber(String invoiceNumber);
	
	public Invoice findByInvoiceId(String id);
	
	@Query(value = "SELECT * FROM Invoice i WHERE i.nature = :nature AND i.create_at BETWEEN :startDate AND :endDate ORDER BY i.create_at DESC", nativeQuery = true)
	public Page<Invoice> invoicesPerNature(@Param("nature")String nature,@Param("startDate")Date startDate,@Param("endDate") Date endDate,PageRequest pageRequest);
	
	@Query(value = "SELECT * FROM Invoice i WHERE i.nature = :nature AND i.create_at BETWEEN :startDate AND :endDate ", nativeQuery = true)
	public List<Invoice> invoicesSumPerNature(@Param("nature") String nature,@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
}

package com.ogivesas.journal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ogivesas.journal.models.Invoice;


public interface InvoiceRepository extends JpaRepository<Invoice, String>{

}

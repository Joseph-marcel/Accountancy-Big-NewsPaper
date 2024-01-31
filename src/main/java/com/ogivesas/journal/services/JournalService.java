package com.ogivesas.journal.services;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.ogivesas.journal.models.Allowance;
import com.ogivesas.journal.models.Contractor;
import com.ogivesas.journal.models.Customer;
import com.ogivesas.journal.models.Invoice;

public interface JournalService {
	
	//CRUD entity Contractor
	public Contractor addCompany(Invoice invoice);
	public Contractor getCompanyByName(String name);
	
	
	//CRUD entity Allowance
	public Allowance addAllowance(Invoice invoice);
	public Allowance getAllowanceByAllowanceName(String name);
	public Allowance getAllowanceById(Long id);
	
	
	//CRUD entity Customer
	public void addCustomer(Customer ctsm);
	public Customer getCustomerByName(String name);
	
	
	//CRUD entity Invoice
	public void newInvoice(Invoice invoice);
	public Page<Invoice> listInvoices(int page,int size);
	public Page<Invoice> listInvoicesPerSearchDate(Date createAt,int page,int size);
	public Invoice getInvoiceByInvoiceId(String id);
	public void updateInvoice(Invoice invoice);
	public void saveInvoice(Invoice invoice);
	public void deleteInvoice(String id);
	
}

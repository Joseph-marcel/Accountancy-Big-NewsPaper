package com.ogivesas.journal.services;

import java.util.Date;


import org.springframework.data.domain.Page;

import com.ogivesas.journal.models.Allowance;
import com.ogivesas.journal.models.Contractor;
import com.ogivesas.journal.models.Customer;
import com.ogivesas.journal.models.Invoice;

public interface JournalService {
	
	public Customer initCustomer();

	//CRUD entity Company
	public Contractor addContractor(Contractor company);
	public Contractor editContractor(Long company_id,Contractor company);
	public Contractor getContractor(Long company_id); 
	public void    deleteContractor(Long company_id);
	public Page<Contractor> listContractors(int page,int size);
	
	
	//CRUD entity Allowance
	public Allowance addAllowance(Allowance allowance);
	public Allowance editAllowance(Long allowance_id,Allowance allowance);
	public Allowance getAllowance(Long allowance_id);
	public void    deleteAllowance(Long allowance_id);
	public Page<Allowance> listAllowances(int page,int size);
	
	
	//CRUD entity invoice
	public Invoice addInvoice(Invoice invoice);
	public Invoice editInvoice(String invoice_id,Invoice invoice);
	public Invoice getInvoice(String invoice_id);
	public void    deleteInvoice(String invoice_id);
	public Page<Invoice> MonthlyInvoices(int page,int size,Date started_date,Date ended_date);
	
}

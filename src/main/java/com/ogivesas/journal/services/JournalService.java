package com.ogivesas.journal.services;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import com.ogivesas.journal.models.Allowance;
import com.ogivesas.journal.models.Contractor;
import com.ogivesas.journal.models.Customer;
import com.ogivesas.journal.models.Invoice;

public interface JournalService {
	
	//CRUD entity Contractor
	public Contractor addCompany(Invoice invoice);
	public Contractor getCompanyByName(String name);
	public Page<Contractor> listContractors(String type,int page,int size);
	public Contractor  getCompanyById(Long id);
	public void updateContractor(Contractor contractor);
	
	
	
	
	//CRUD entity Allowance
	public Allowance addAllowance(Invoice invoice);
	public Allowance getAllowanceByAllowanceName(String name);
	public Allowance getAllowanceById(Long id);
	public Page<Allowance> listAllowances(int page,int size);
	public void updateAllowance(Allowance allowance);
	
	
	
	
	//CRUD entity Customer
	public void addCustomer(Customer ctsm);
	public Customer getCustomerByName(String name);
	
	
	
	
	//CRUD entity Invoice
	public Invoice newInvoice(Invoice invoice);
	public Page<Invoice> listInvoices(int page,int size);
	public Page<Invoice> listInvoicesPerSearchDate(Date createAt,int page,int size);
	public Invoice getInvoiceByInvoiceId(String id);
	public Invoice updateInvoice(Invoice invoice);
	public Invoice saveInvoice(Invoice invoice);
	public void deleteInvoice(String id);
	public Page<Invoice> monthlyInvoices(Date startDate, Date endDate,int page,int size);
	public List<Invoice> invoicesPerMonth(Date startDate, Date endDate);
	
}

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
      Contractor addCompany(Invoice invoice);
	  Contractor getCompanyByName(String name);
	  Page<Contractor> listContractors(String type,int page,int size);
	  Contractor  getCompanyById(Long id);
	  Contractor updateContractor(Contractor contractor);
	  List<Contractor> getAllTenders(String type);
	
	
	//CRUD entity Allowance
	 Allowance addAllowance(Invoice invoice);
	 Allowance getAllowanceByAllowanceName(String name);
	 Allowance getAllowanceById(Long id);
	 Page<Allowance> listAllowances(int page,int size);
	 Allowance updateAllowance(Allowance allowance);
	 List<Allowance> getAllAllowances();
	
	
	//CRUD entity Customer
	 void addCustomer(Customer ctsm);
	      Customer getCustomerByName(String name);
	
	
	//CRUD entity Invoice
	 Invoice newInvoice(Invoice invoice);
	 Page<Invoice> listInvoices(int page,int size);
	 Page<Invoice> listInvoicesPerSearchDate(Date createAt,int page,int size);
	 Invoice getInvoiceByInvoiceId(String id);
	 Invoice updateInvoice(Invoice invoice);
	 Invoice saveInvoice(Invoice invoice);
	 void deleteInvoice(String id);
	 Page<Invoice> monthlyInvoices(Date startDate, Date endDate,int page,int size);
	 List<Invoice> invoicesPerMonth(Date startDate, Date endDate);
	 List<Invoice> invoicesSum(String nature,Date startDate, Date endDate);
}

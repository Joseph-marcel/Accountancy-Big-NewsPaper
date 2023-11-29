package com.ogivesas.journal.servicesImpl;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogivesas.journal.models.Allowance;
import com.ogivesas.journal.models.Contractor;
import com.ogivesas.journal.models.Customer;
import com.ogivesas.journal.models.Invoice;
import com.ogivesas.journal.repositories.AllowanceRepository;
import com.ogivesas.journal.repositories.CompanyRepository;
import com.ogivesas.journal.repositories.InvoiceRepository;
import com.ogivesas.journal.services.JournalService;

import lombok.AllArgsConstructor;
import lombok.Data;


@Service
@Transactional
@AllArgsConstructor
public class JournalServiceImpl implements JournalService{
	
	private AllowanceRepository allowanceRepo;
	private CompanyRepository   companyRepo;
	private InvoiceRepository   invoiceRepo;
	
	
	
	

	@Override
	public Customer initCustomer() {
		// TODO Auto-generated method stub
				             
		return;
	}

	@Override
	public Contractor addContractor(Contractor company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contractor editContractor(Long company_id, Contractor company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contractor getContractor(Long company_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteContractor(Long company_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Contractor> listContractors(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Allowance addAllowance(Allowance allowance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Allowance editAllowance(Long allowance_id, Allowance allowance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Allowance getAllowance(Long allowance_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllowance(Long allowance_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Allowance> listAllowances(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoice addInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoice editInvoice(String invoice_id, Invoice invoice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoice getInvoice(String invoice_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInvoice(String invoice_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Invoice> MonthlyInvoices(int page, int size, Date started_date, Date ended_date) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}

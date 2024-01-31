package com.ogivesas.journal.servicesImpl;


import java.util.Date;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ogivesas.journal.models.Allowance;
import com.ogivesas.journal.models.Contractor;
import com.ogivesas.journal.models.Customer;
import com.ogivesas.journal.models.Director;
import com.ogivesas.journal.models.Invoice;
import com.ogivesas.journal.repositories.AllowanceRepository;
import com.ogivesas.journal.repositories.CompanyRepository;
import com.ogivesas.journal.repositories.InvoiceRepository;
import com.ogivesas.journal.services.JournalService;
import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class JournalServiceImpl implements JournalService{
	
	public  AllowanceRepository allowanceRepo;
	public  CompanyRepository   companyRepo;
	public  InvoiceRepository   invoiceRepo;
	
   
	
	
    //CRUD CONTRACTOR
	@Override
	public Contractor addContractor(Contractor company) {
		// TODO Auto-generated method stub
		
		company = Director.contractorBuilder()
				.name(company.getName())
				.taxPayNumber(company.getTaxPayerNumber())
				.email(company.getEmail())
				.phoneNumber(company.getPhoneNumber())
				.invoices()
				.build();
		return companyRepo.save(company);
	}

	@Override
	public Contractor editContractor(Long company_id, Contractor contractor) {
		// TODO Auto-generated method stub
		
		Contractor existingContractor = this.getContractor(company_id);
		           existingContractor.setName(contractor.getName());
		           existingContractor.setEmail(contractor.getEmail());
		           existingContractor.setPhoneNumber(contractor.getPhoneNumber());
		           existingContractor.setTaxPayerNumber(contractor.getTaxPayerNumber());
		           
		return companyRepo.save(existingContractor);
	}

	@Override
	public Contractor getContractor(Long company_id) {
		// TODO Auto-generated method stub
		
		return (Contractor) companyRepo.findById(company_id).orElse(null);
	}

	@Override
	public void deleteContractor(Long company_id) {
		// TODO Auto-generated method stub
		
		Contractor contractor = this.getContractor(company_id);
		companyRepo.delete(contractor);
		
	}

	@Override
	public Page<Contractor> listContractors(String type, int page, int size) {
		// TODO Auto-generated method stub
		
		return companyRepo.listContractors(type, PageRequest.of(page, size));
	}
	

	@Override
	public Contractor getContractorByName(String name) {
		// TODO Auto-generated method stub
		
		return companyRepo.findByName(name);
	}

	
	
	
	//CRUD ALLOWANCE
	@Override
	public Allowance addAllowance(Allowance allowance) {
		// TODO Auto-generated method stub
		
		Customer cstm = this.initialCustomer();
		allowance = Director.allowanceBuilder()
				.allowanceName(allowance.getAllowanceName())
				.customer(cstm)
				.build();
		return allowanceRepo.save(allowance);
	}

	@Override
	public Allowance editAllowance(Long allowance_id, Allowance allowance) {
		// TODO Auto-generated method stub
		
		Allowance existingAllowance = this.getAllowance(allowance_id);
		          existingAllowance.setAllowanceName(allowance.getAllowanceName());
		return allowanceRepo.save(existingAllowance);
	}

	@Override
	public Allowance getAllowance(Long allowance_id) {
		// TODO Auto-generated method stub
		
		return (Allowance) allowanceRepo.findById(allowance_id).orElse(null);
	}
	
	@Override
	public Allowance getAllowanceByName(String name) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findByAllowanceName(name);
	}

	@Override
	public void deleteAllowance(Long allowance_id) {
		// TODO Auto-generated method stub
		
		Allowance allowance = this.getAllowance(allowance_id);
		allowanceRepo.delete(allowance);
	}

	@Override
	public Page<Allowance> listAllowances(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	
	//CRUD INVOICE
	@Override
	public Invoice addInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		
		Contractor contractor = this.getContractorByName(invoice.getContractor().getName());
		Allowance allowance = this.getAllowanceByName(invoice.getAllowance().getAllowanceName());
		if(contractor == null){
			contractor = this.addContractor(contractor);
			if(allowance == null){
				allowance = this.addAllowance(allowance);
			}
		}else {
		    if(allowance == null){
		    	allowance = this.addAllowance(allowance);
		    }
		}
		
		  invoice = Director.invoiceBuilder()
				.invoiceId(UUID.randomUUID().toString())
				.invoiceNumber(invoice.getInvoiceNumber())
				.date(invoice.getDate())
				.amount(invoice.getAmount())
				.contractor(contractor)
				.allowance(allowance)
				.build();
		   
		
		return invoiceRepo.save(invoice);
	}

	@Override
	public Invoice editInvoice(String invoice_id, Invoice invoice) {
		// TODO Auto-generated method stub
		
		Invoice existingInvoice = this.getInvoice(invoice_id);
		        existingInvoice.setAllowance(invoice.getAllowance());
		        existingInvoice.setAmount(invoice.getAmount());
		        existingInvoice.setContractor(invoice.getContractor());
		        existingInvoice.setDate(invoice.getDate());
		        existingInvoice.setInvoiceNumber(invoice.getInvoiceNumber());
		        
		        
		return invoiceRepo.save(existingInvoice);
	}

	@Override
	public Invoice getInvoice(String invoice_id) {
		// TODO Auto-generated method stub
		return (Invoice) invoiceRepo.findById(invoice_id).orElse(null);
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

	@Override
	public Customer initialCustomer() {
		// TODO Auto-generated method stub
		
		Customer customer = Director.customerBuilder()
				.name("OGIVE SAS")
				.taxPayNumber("M091612571014S")
				.email("siogivesas@gmail.com")
				.phoneNumber("(+237) 694 674 286/694 467 982")
				.allowances()
				.build();
		return companyRepo.save(customer);
	}

}

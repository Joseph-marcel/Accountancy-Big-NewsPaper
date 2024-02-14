package com.ogivesas.journal.servicesImpl;


import java.util.Date;
import java.util.List;
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

	
	
	//CRUD entity Contractor
	@Override
	public Contractor addCompany(Invoice invoice) {
		// TODO Auto-generated method stub
		
		Contractor contractor = Director.contractorBuilder()
				.name(invoice.getContractor().getName())
				.taxPayerNumber(invoice.getContractor().getTaxPayerNumber())
				.email(invoice.getContractor().getEmail())
				.phoneNumber(invoice.getContractor().getPhoneNumber())
				.invoices()
				.build();
		return companyRepo.save(contractor);
	}
	
	
	@Override
	public Contractor getCompanyByName(String name) {
		// TODO Auto-generated method stub
		
		
		return  (Contractor)companyRepo.findByName(name);
	}
	
	
	@Override
	public Contractor getCompanyById(Long id) {
		// TODO Auto-generated method stub
		
		return (Contractor)companyRepo.findById(id).orElse(null);
	}

	
	@Override
	public Page<Contractor> listContractors(String type,int page, int size) {
		// TODO Auto-generated method stub
		
		return (Page<Contractor>)companyRepo.listContractors(type, PageRequest.of(page, size));
	}
	
	
	@Override
	public void updateContractor(Contractor contractor) {
		// TODO Auto-generated method stub
		
		Contractor savedContractor = this.getCompanyById(contractor.getCompanyId());
		           savedContractor.setName(contractor.getName());
		           savedContractor.setTaxPayerNumber(contractor.getTaxPayerNumber());
		           savedContractor.setEmail(contractor.getEmail());
		           savedContractor.setPhoneNumber(contractor.getPhoneNumber());
		           
		           companyRepo.save(savedContractor);
	}


	
	
	//CRUD entity Allowance
	@Override
	public Allowance addAllowance(Invoice invoice) {
		// TODO Auto-generated method stub
		
		Allowance allowance = Director.allowanceBuilder()
				.allowanceName(invoice.getAllowance().getAllowanceName())
				.customer(invoice.getAllowance().getCustomer())
				.invoices()
				.build();
		
		return allowanceRepo.save(allowance);
	}

	
	@Override
	public Allowance getAllowanceByAllowanceName(String name) {
		// TODO Auto-generated method stub
		
		
		return allowanceRepo.findByAllowanceName(name);
	}

	@Override
	public Allowance getAllowanceById(Long id) {
		// TODO Auto-generated method stub
		
		Allowance allowance = allowanceRepo.findById(id).orElse(null);
		
		if(allowance == null) throw new RuntimeException("Cette prestation n'existe pas.");
		
		return allowance;
	}
	
	
	@Override
	public Page<Allowance> listAllowances(int page, int size) {
		// TODO Auto-generated method stub
		
		return allowanceRepo.findAll(PageRequest.of(page, size));
	}

	
	
	
	
	//CRUD entity Customer
	@Override
	public void addCustomer(Customer cstm) {
		// TODO Auto-generated method stub
		
		
		companyRepo.save(cstm);
	}
	
	
	
	
	//CRUD entity Invoice
	@Override
	public void newInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		
		  Contractor contractor = this.getCompanyByName(invoice.getContractor().getName());
		  Allowance allowance = this.getAllowanceByAllowanceName(invoice.getAllowance().getAllowanceName());
		  
		  if(contractor == null){ 
			  if(allowance == null){ 
				  contractor = this.addCompany(invoice);
				  allowance = this.addAllowance(invoice);
				  } else { 
					  contractor = this.addCompany(invoice);
				   } 
			      } else { 
			    	  if(allowance == null) {
		             allowance = this.addAllowance(invoice);
		             } 
			      }
					
					  invoice = Director.invoiceBuilder() 
							  .invoiceId(UUID.randomUUID().toString())
					          .invoiceNumber(invoice.getInvoiceNumber()) 
					          .createAt(invoice.getCreateAt())
					          .amount(invoice.getAmount()) 
					          .contractor(contractor) 
					          .allowance(allowance)
					  .build();
						
						
						  invoiceRepo.save(invoice);
	}

	
	@Override
	public Page<Invoice> listInvoices(int page, int size) {
		// TODO Auto-generated method stub
		
		return invoiceRepo.listInvoices(PageRequest.of(page, size));
	}

	@Override
	public Customer getCustomerByName(String name) {
		// TODO Auto-generated method stub
		
		Customer cstm = (Customer)companyRepo.findByName(name);
		
		if(cstm == null) throw new RuntimeException("Ce prestataire n'existe pas.");
		
		return cstm;
	}

	@Override
	public Page<Invoice> listInvoicesPerSearchDate(Date createAt,int page, int size) {
		// TODO Auto-generated method stub
		
		return invoiceRepo.listInvoicesPerSearchDate(createAt, PageRequest.of(page, size));
	}

	@Override
	public Invoice getInvoiceByInvoiceId(String id) {
		// TODO Auto-generated method stub
		
		
		return invoiceRepo.findByInvoiceId(id);
	}

	
	
	@Override
	public void updateInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		
		 Invoice savedInvoice = this.getInvoiceByInvoiceId(invoice.getInvoiceId());
		 savedInvoice.setInvoiceNumber(invoice.getInvoiceNumber());
		 savedInvoice.setCreateAt(invoice.getCreateAt());
		 savedInvoice.setAmount(invoice.getAmount());
		 
		 invoiceRepo.save(savedInvoice);
	}

	
	
	@Override
	public void saveInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		
		if(invoice.getInvoiceId() != null) {
			this.updateInvoice(invoice);
		}else {
			this.newInvoice(invoice);
		}
		
	}

	
	@Override
	public void deleteInvoice(String id) {
		// TODO Auto-generated method stub
		
		
		 invoiceRepo.deleteById(id);  
	}


	@Override
	public void updateAllowance(Allowance allowance) {
		// TODO Auto-generated method stub
		
		Allowance savedAllowance = this.getAllowanceById(allowance.getAllowanceId());
		          savedAllowance.setAllowanceName(allowance.getAllowanceName());
		          savedAllowance.setCustomer(allowance.getCustomer());
		          
		allowanceRepo.save(savedAllowance);
	}


	@Override
	public Page<Invoice> monthlyInvoices(Date startDate, Date endDate,int page,int size) {
		// TODO Auto-generated method stub
		
		return invoiceRepo.listInvoicesPerMonth(startDate, endDate,PageRequest.of(page, size));
	}


	@Override
	public List<Invoice> invoicesPerMonth(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		
		return invoiceRepo.invoicesPerMonth(startDate, endDate);
	}


    
}

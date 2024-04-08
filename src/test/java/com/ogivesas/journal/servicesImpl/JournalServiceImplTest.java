package com.ogivesas.journal.servicesImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.ogivesas.journal.models.Allowance;
import com.ogivesas.journal.models.Contractor;
import com.ogivesas.journal.models.Director;
import com.ogivesas.journal.models.Invoice;
import com.ogivesas.journal.repositories.AllowanceRepository;
import com.ogivesas.journal.repositories.CompanyRepository;
import com.ogivesas.journal.repositories.InvoiceRepository;




class JournalServiceImplTest {
	
	@InjectMocks
	private JournalServiceImpl journalServiceImpl;

	@Mock
	private  AllowanceRepository allowanceRepo;
	
	@Mock
	private  CompanyRepository   companyRepo;
	
	@Mock
	private  InvoiceRepository   invoiceRepo;
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testListInvoiceOk() throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); String
		  text="2024-04-03"; Date date = format.parse(text);
		  Contractor contractor = new Contractor();
		  contractor.setName("Orange");
		  Allowance allowance = new Allowance(); allowance.setAllowanceName("Frais");
		  
		List<Invoice> invoices1 = List.of(
				Director.invoiceBuilder()
				.invoiceId("o1236588")
				.invoiceNumber("4lkkdudd")
				.createAt(date)
				.contractor(contractor)
				.allowance(allowance)
				.build()
				);
		
		//Mockito.when(invoiceRepo.listInvoices(PageRequest.of(1, 6))).thenReturn(invoices.stream().map(i -> {
			//Page<Invoice>
		
		var invoices = mock(Page.class);
        when(invoiceRepo.listInvoices(any(PageRequest.class))).thenReturn(invoices);

        // Act
        journalServiceImpl.listInvoices(1,10);

        // Assert
        verify(invoiceRepo).listInvoices(any(PageRequest.class));
        verifyNoMoreInteractions(invoiceRepo);

		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testListAllowancesOk() {
		
		var allowances = mock(Page.class);
		
		when(allowanceRepo.findAll(any(PageRequest.class))).thenReturn(allowances);
		
		journalServiceImpl.listAllowances(1, 6);
		
		verify(allowanceRepo).findAll(any(PageRequest.class));
		verifyNoMoreInteractions(allowanceRepo);
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testListContractorsOk() {
		
		var contractors = mock(Page.class);
		
		when(companyRepo.listContractors(any(), any(PageRequest.class))).thenReturn(contractors);
		
		journalServiceImpl.listContractors("CONTRACT", 1, 6);
		
		verify(companyRepo).listContractors(any(), any(PageRequest.class));
		verifyNoMoreInteractions(companyRepo);
	}
	
	
	@Test
	public void shouldReturnAllowanceByIdOk() {
		
		Long allowancedId = 1L;
		Allowance allowance = new Allowance();
		          allowance.setAllowanceName("Frais");
		          
		when(allowanceRepo.findById(anyLong())).thenReturn(Optional.ofNullable(allowance));
		
		Allowance savedAllowance = journalServiceImpl.getAllowanceById(allowancedId);
		
		assertEquals(allowance.getAllowanceName(), savedAllowance.getAllowanceName());
		assertEquals(allowance.getAllowanceId(), savedAllowance.getAllowanceId());
		
		verify(allowanceRepo, times(1)).findById(allowancedId);
	}
	
	
	@Test
	public void shouldReturnContractorByIdOk() {
		
		Long companyId = 1L;
		Contractor contractor = new Contractor();
		           contractor.setName("Bourse du savoir plus 1");
		           contractor.setTaxPayerNumber("P058000439730L");
		           contractor.setPhoneNumber("680626229");
		           
	    when(companyRepo.findById(anyLong())).thenReturn(Optional.ofNullable(contractor));
	    
	    Contractor savedContractor = journalServiceImpl.getCompanyById(companyId);
	    
	    assertEquals(contractor.getCompanyId(), savedContractor.getCompanyId());
	    assertEquals(contractor.getName(), savedContractor.getName());
	    assertEquals(contractor.getTaxPayerNumber(), savedContractor.getTaxPayerNumber());
	    assertEquals(contractor.getPhoneNumber(), savedContractor.getPhoneNumber());
	    
	    verify(companyRepo, times(1)).findById(companyId);
	}
	
	
	@Test
	public void shouldReturnAllowanceByNameOk() {
		
		String name = "Facture d'électricité";
		Allowance allowance = new Allowance();
		          allowance.setAllowanceName(name);
		          
		
		when(allowanceRepo.findByAllowanceName(anyString())).thenReturn(allowance);
		
		Allowance existingAllowance = journalServiceImpl.getAllowanceByAllowanceName(name);
		
		assertEquals(allowance.getAllowanceName(), existingAllowance.getAllowanceName());
		verify(allowanceRepo, times(1)).findByAllowanceName(name);
	}
	
	
	@Test
	public void shouldReturnContractorByNameOk() {
		
		String name = "Bourse du savoir plus 1";
		Contractor contractor = new Contractor();
				   contractor.setName("Bourse du savoir plus 1");
			       contractor.setTaxPayerNumber("P058000439730L");
			       contractor.setPhoneNumber("680626229");
		          
		
		when(companyRepo.findByName(anyString())).thenReturn(contractor);
		
		Contractor existingContractor = journalServiceImpl.getCompanyByName(name);
		
		assertEquals(contractor.getName(), existingContractor.getName());
		assertEquals(contractor.getTaxPayerNumber(), existingContractor.getTaxPayerNumber());
		assertEquals(contractor.getPhoneNumber(), existingContractor.getPhoneNumber());
		verify(companyRepo, times(1)).findByName(name);
	}
	

}

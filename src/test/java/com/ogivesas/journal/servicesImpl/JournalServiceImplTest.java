package com.ogivesas.journal.servicesImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.ogivesas.journal.models.Customer;
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
	
	
	@Test
	public void shouldUpdateAllowance() {
		
		Customer customer = Director.customerBuilder()
				.name("OGIVE SAS")
				.companyId(1L)
				.taxPayerNumber("M091612571014S")
				.email("siogivesas@gmail.com")
				.phoneNumber("(+237) 694 674 286 / 694467 982")
				.build();
		
		Allowance allowance = Director.allowanceBuilder()
				.allowanceId(1L)
				.allowanceName("Updated name")
				.customer(customer)
				.build();
		
		Allowance savedAllowance = Director.allowanceBuilder()
				.allowanceName("fournitures")
				.build();
		
		when(allowanceRepo.findById(anyLong())).thenReturn(Optional.ofNullable(savedAllowance));
		when(allowanceRepo.save(savedAllowance)).thenReturn(savedAllowance);
		
		Allowance updatedAllowance = journalServiceImpl.updateAllowance(allowance);
		
		assertEquals(savedAllowance.getAllowanceName(), updatedAllowance.getAllowanceName());
		
	}
	
	
	@Test
	public void shouldUpdateContractor() {
		
		Contractor contractor = Director.contractorBuilder()
				.companyId(2L)
				.name("Alex Copys")
				.email("nfcBank@hotmail.fr")
				.taxPayerNumber("M079100006355X")
				.build();
		    
		Contractor savedContractor = Director.contractorBuilder()
				.name("Alex Copy")
				.build();
		
		when(companyRepo.findById(anyLong())).thenReturn(Optional.ofNullable(savedContractor));
		when(companyRepo.save(savedContractor)).thenReturn(savedContractor);
		
		Contractor updatedContractor = journalServiceImpl.updateContractor(contractor);
		
		assertEquals(savedContractor.getName(), updatedContractor.getName());
	}
	
	
	  @SuppressWarnings("unused")
	  @Test 
	  public void shouldSaveAllowance() throws ParseException {
	  
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		  String  text="2024-04-03"; 
		  Date date = format.parse(text);
		  
		  Customer customer = Director.customerBuilder()
				                      .name("OGIVE SAS")
		                              .companyId(1L) 
		                              .taxPayerNumber("M091612571014S")
		                              .email("siogivesas@gmail.com")
		                              .phoneNumber("(+237) 694 674 286 / 694467 982")
		                              .build();
	  
		  Allowance allowance = Director.allowanceBuilder()
		                                .allowanceName("Entretien vehicule") 
		                                .customer(customer)
		                                .build();
		  
		  Contractor contractor = Director.contractorBuilder()
				                          .name("Garage DG")
				                          .taxPayerNumber("M079100006357Y")
				                          .email("dg@caramail.com")
				                          .build();
		  
		  Invoice invoice = Director.invoiceBuilder()
				                    .invoiceNumber("dg-0154")
				                    .amount(25000)
				                    .createAt(date)
				                    .allowance(allowance)
				                    .contractor(contractor)
				                    .build();
		  
		  Allowance savedAllowance = Director.allowanceBuilder()
				                             .allowanceId(2L)
				                             .allowanceName("Entretien vehicule")
				                             .customer(customer)
				                             .build();
		  
		  Allowance newAllowance = journalServiceImpl.addAllowance(invoice);
		  
		  verify(allowanceRepo, times(1)).save(any());
	  
	  }
	  
	  
	  
	@SuppressWarnings("unused")
	@Test
	  public void shouldSaveContractor() throws ParseException {
		  
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		  String  text="2024-04-05"; 
		  Date date = format.parse(text);
		  
		  Customer customer = Director.customerBuilder()
				                      .name("OGIVE SAS")
		                              .companyId(1L) 
		                              .taxPayerNumber("M091612571014S")
		                              .email("siogivesas@gmail.com")
		                              .phoneNumber("(+237) 694 674 286 / 694467 982")
		                              .build();
	  
		  Allowance allowance = Director.allowanceBuilder()
		                                .allowanceName("Entretien vehicule") 
		                                .customer(customer)
		                                .build();
		  
		  Contractor contractor = Director.contractorBuilder()
				                          .name("Garage DG")
				                          .taxPayerNumber("M079100006357Y")
				                          .email("dg@caramail.com")
				                          .build();
		  
		  Invoice invoice = Director.invoiceBuilder()
				                    .invoiceNumber("dg-0154")
				                    .amount(25000)
				                    .createAt(date)
				                    .allowance(allowance)
				                    .contractor(contractor)
				                    .build();
		  
		  Contractor savedContractor = Director.contractorBuilder()
                                               .name("Garage DG")
                                               .taxPayerNumber("M079100006357Y")
                                               .email("dg@caramail.com")
                                               .build();
		  
		  Contractor newContractor = journalServiceImpl.addCompany(invoice);
		  
		  verify(companyRepo, times(1)).save(any());
		  
	  }
	
	
	
		/*
		 * @Test public void allowanceByIdShouldThrowsNullPointerException() {
		 * 
		 * Long id = 1L; var exp = assertThrows(NullPointerException.class, () ->
		 * journalServiceImpl.getAllowanceById(id));
		 * assertEquals("Cette prestation n'existe pas.", exp.getMessage()); }
		 */
	
	
	
	
		/*
		 * @Test public void allowanceByNameShouldThrowsNullPointerException() {
		 * 
		 * String name = "fournitures"; var exp =
		 * assertThrows(NullPointerException.class, () ->
		 * journalServiceImpl.getAllowanceByAllowanceName(name));
		 * assertEquals("Cette prestation n'existe pas.", exp.getMessage()); }
		 */
	
	
	/*
	 * @Test public void contractorByIdShouldThrowsNullPointerException() {
	 * 
	 * Long id = 1L; var exp = assertThrows(NullPointerException.class, () ->
	 * journalServiceImpl.getCompanyById(id));
	 * assertEquals("Ce prestataire n'existe pas.", exp.getMessage()); }
	 */
	
	
	/*
	 * @Test public void contractorByNameShouldThrowsNullPointerException() {
	 * 
	 * String name = "Alex Copy"; var exp = assertThrows(NullPointerException.class,
	 * () -> journalServiceImpl.getCompanyByName(name));
	 * assertEquals("Ce prestataire n'existe pas.", exp.getMessage()); }
	 */
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testInvoicesPerSearchdate() throws ParseException {
		
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		 String  text="2024-04-05"; 
		 Date date = format.parse(text);
		 
		 var invoices = mock(Page.class);
		 
		 when(invoiceRepo.listInvoicesPerSearchDate(any(Date.class), any(PageRequest.class))).thenReturn(invoices);
		 
		 journalServiceImpl.listInvoicesPerSearchDate(date, 1, 6);
		 
		 verify(invoiceRepo).listInvoicesPerSearchDate(any(Date.class), any(PageRequest.class));
		 verifyNoMoreInteractions(invoiceRepo);
	}
	 
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testInvoicesForPeriod() throws ParseException{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		String  text1="2024-02-05"; 
		String  text2="2024-04-05";
		Date date1 = format.parse(text1);
		Date date2 = format.parse(text2);
		
		var invoices = mock(Page.class);
		
		when(invoiceRepo.listInvoicesPerMonth(any(Date.class), any(Date.class), any(PageRequest.class))).thenReturn(invoices);
		
		journalServiceImpl.monthlyInvoices(date1, date2, 1, 6);
		
		verify(invoiceRepo).listInvoicesPerMonth(any(Date.class), any(Date.class), any(PageRequest.class));
		verifyNoMoreInteractions(invoiceRepo);
	}
	
	
	
	
	
	
	
	
	
	
}

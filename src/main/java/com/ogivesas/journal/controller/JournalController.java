package com.ogivesas.journal.controller;

import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ogivesas.journal.models.Allowance;
import com.ogivesas.journal.models.Contractor;
import com.ogivesas.journal.models.Customer;
import com.ogivesas.journal.models.Director;
import com.ogivesas.journal.models.Invoice;
import com.ogivesas.journal.services.JournalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class JournalController {

	private JournalService iJournalService;

	
	//Controller Invoice methods
	
	@GetMapping("/index")
	public String index(Model model,
			@RequestParam(name = "page", defaultValue = "0")int page,
			@RequestParam(name = "size", defaultValue = "6")int size) {
		
		
		try {
			
			Page<Invoice> listInvoices = iJournalService.listInvoices(page, size);
			model.addAttribute("invoices", listInvoices.getContent());
			int[] pages = new int[listInvoices.getTotalPages()]; 
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", page);
			
		}catch (Exception e){
			
			model.addAttribute("exception",e);
		}
		
		
		return "homePage";
	}
	
	
	
	@GetMapping("/formInvoice")
	public String register(Model model) {
		
		Invoice invoice = Director.invoiceBuilder()
				.build();
		
		Customer cstm = iJournalService.getCustomerByName("OGIVE SAS");
		
		Allowance allowance = invoice.getAllowance();
		allowance.setCustomer(cstm);
		
	     model.addAttribute("invoice", invoice);
	     			 		
		return "formInvoice";
	}
	
	
	@PostMapping("/createInvoice")
	public String newInvoice(Model model,@Valid Invoice invoice,
			BindingResult bindingResult,
			@RequestParam(defaultValue="0")int page) {
		
		
		  if(bindingResult.hasErrors()){
		  
		  return "formInvoice"; 
		  }
		  
		  String message ="Enregistrement réussi...!!!";
		  iJournalService.saveInvoice(invoice);
		  model.addAttribute("message", message);
		 
		return "redirect:/index";
	}
	
	
	
	@GetMapping("/searchInvoice")
	public String searchInvoice(Model model,@DateTimeFormat(pattern = "yyyy-MM-dd") Date createAt,
			@RequestParam(name = "page", defaultValue = "0")int page,
			@RequestParam(name = "size", defaultValue = "6")int size) {
			
			  model.addAttribute("createAt", createAt);
			  
				  try { 
					  
					  Page<Invoice> listInvoicesP = iJournalService.listInvoicesPerSearchDate(createAt, page, size);
				      model.addAttribute("invoices",listInvoicesP.getContent());
				      int[] pages = new  int[listInvoicesP.getTotalPages()];
				      model.addAttribute("pages", pages);
				      model.addAttribute("currentPage", page);
				      
				  } catch(Exception e){
					  model.addAttribute("exception",e);
					  }
				 
		    
		return "homePage";
	}
	
	
	@GetMapping("/editInvoice")
	public String editInvoice(Model model,String id,
			@RequestParam(defaultValue = "0")int page) {
		    
		try {
			
			Invoice invoice = iJournalService.getInvoiceByInvoiceId(id);
			
		    model.addAttribute("invoice", invoice);
		    model.addAttribute("page", page);
		
		} catch(Exception e) {
			model.addAttribute("exception",e);
		}
		    
		    
		return "formEditInvoice";
	}
	
	
	@PostMapping("/updateInvoice")
	public String updateInvoice(@RequestParam(defaultValue = "0")int page, Invoice invoice) {
		
		Invoice savedInvoice = iJournalService.getInvoiceByInvoiceId(invoice.getInvoiceId());
		
		
		  savedInvoice.setInvoiceNumber(invoice.getInvoiceNumber());
		  savedInvoice.setCreateAt(invoice.getCreateAt());
		  savedInvoice.setAmount(invoice.getAmount());
		 
		
		iJournalService.saveInvoice(savedInvoice);
		
		return "redirect:/index?page="+page;
	}
	
	
	@GetMapping("/deleteInvoice")
	public String deleteInvoice(@RequestParam(defaultValue = "0")int page,@RequestParam(name = "id") String id) {
		
		iJournalService.deleteInvoice(id);
		
		return "redirect:/index?page="+page;
	}
	
	
	
	//Controller Contractor method
	
	
	@GetMapping("/prestataires")
	public String prestataires(Model model,String type,
			@RequestParam(name = "page", defaultValue = "0")int page,
			@RequestParam(name = "size", defaultValue = "6")int size) {
		
		try {
			  
			type = "CONTRACT";
			Page<Contractor> listContractors = iJournalService.listContractors(type,page, size);
			model.addAttribute("contractors", listContractors.getContent());
			int[] pages = new int[listContractors.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", page);
			
		}catch (Exception e) {
			
			model.addAttribute("exception",e);
		}
		
		
		return "indexPrestataires";
	}
	
	
	@GetMapping("/editContractor")
	public String editContractor(Model model,Long id,
			@RequestParam(name = "page", defaultValue = "0")int page) {
		
		    Contractor contractor = Director.contractorBuilder().build();
		
		try {
			
		    contractor = iJournalService.getCompanyById(id);
		    model.addAttribute("contractor", contractor);
		    model.addAttribute("page", page);
		    
		}catch(Exception e){
			  model.addAttribute("exception",e);
			  }
		    
		
		return "editContractor";
	}
	
	
	@PostMapping("/updateContractor")
	public String updateContractor(@RequestParam(defaultValue = "0")int page,Contractor contractor) {
		 
		System.out.println(contractor.getCompanyId()+" "+contractor.getEmail()+" "+contractor.getTaxPayerNumber());
		iJournalService.updateContractor(contractor); 
		
		return "redirect:/prestataires?page="+ page;
	}
	
	
	
	
	//Controller Allowance methods
	
	@GetMapping("/prestations")
	public String prestations(Model model,
			@RequestParam(name = "page", defaultValue = "0")int page,
			@RequestParam(name = "size", defaultValue = "6")int size) {
		
		
		return "indexPrestations";
	}
	
	
	
}

package com.ogivesas.journal.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ogivesas.journal.configuration.userDetailsConfig.AppRole;
import com.ogivesas.journal.configuration.userDetailsConfig.AppUser;
import com.ogivesas.journal.models.Allowance;
import com.ogivesas.journal.models.Contractor;
import com.ogivesas.journal.models.Customer;
import com.ogivesas.journal.models.Director;
import com.ogivesas.journal.models.Invoice;
import com.ogivesas.journal.services.CustomUserDetailService;
import com.ogivesas.journal.services.JournalService;
import com.ogivesas.journal.servicesImpl.CustomUserDetailServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import utils.FileUpLoadUtil;


@Controller
@AllArgsConstructor
@Slf4j
public class JournalController {

	private JournalService iJournalService;
	private CustomUserDetailService customUserDetailService;
	private CustomUserDetailServiceImpl   appRoleServiceImpl;

	
	//Controller Invoice methods
	
	@GetMapping("/index")
	public String index(Model model,
			@RequestParam(name = "createAt", defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd") Date createAt,
			String message,
			@RequestParam(name = "page", defaultValue = "0")int page,
			@RequestParam(name = "size", defaultValue = "20")int size) {
		    
		        model.addAttribute("message", message);
		    
		try {
			
				if(createAt == null) {
					
					Page<Invoice> listInvoices = iJournalService.listInvoices(page, size);
					model.addAttribute("invoices", listInvoices.getContent());
					int[] pages = new int[listInvoices.getTotalPages()-1];
					model.addAttribute("pages", pages);
					
				 }else {
					 
					 Page<Invoice> listInvoicesP = iJournalService.listInvoicesPerSearchDate(createAt, page, size);
					 model.addAttribute("invoices", listInvoicesP.getContent());
					 int[] pages = new int[listInvoicesP.getTotalPages()-1]; 
					 model.addAttribute("pages", pages);
					 model.addAttribute("createAt", createAt);
					 
				 }
			    
			     model.addAttribute("currentPage", page);
			 
		}catch (Exception e){
			
			model.addAttribute("exception",e);
		}
		
		
		return "homePage";
	}
	
	
	
	@GetMapping("/formInvoice")
	public String register(Model model) {
		
	    Invoice invoice = Director.invoiceBuilder().build();
		
		Customer cstm = iJournalService.getCustomerByName("OGIVE SAS");
		
		Allowance allowance = invoice.getAllowance();
		allowance.setCustomer(cstm);
		
	     model.addAttribute("invoice", invoice);
	     			 		
		return "formInvoice";
	}
	
	
	@PostMapping("/createInvoice")
	public String newInvoice(Model model,@Valid Invoice invoice,
			BindingResult bindingResult,
			String message,
			@RequestParam(defaultValue="0")int page,
			@RequestParam("image") MultipartFile multiPartFile) throws IOException {
		
		
			  if(bindingResult.hasErrors()){
			  
			     return "formInvoice"; 
			  }
			  
			  String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
			         invoice.setImageFileName(fileName);
			  
			  Invoice savedInvoice =  iJournalService.saveInvoice(invoice);
			
			  String upLoadDir = "invoice-images/" + savedInvoice.getInvoiceId();
			         FileUpLoadUtil.saveFile(upLoadDir,fileName,multiPartFile);
		       
		       
			  if(savedInvoice != null) {
			     message ="Nouvelle facture enregistrée...!!!";
			   }
		  
		 
		return "redirect:/index?message="+message;
	}
	
	
	
	@GetMapping("/editInvoice")
	public String editInvoice(Model model,String id,
			@RequestParam(defaultValue = "0")int page,
			@RequestParam(name = "createAt",defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date createAt) {
		    
		try {
			
			Invoice invoice = iJournalService.getInvoiceByInvoiceId(id);
			
		    model.addAttribute("invoice", invoice);
		    model.addAttribute("page", page);
		    model.addAttribute("createAt", createAt);
		
		} catch(Exception e) {
			model.addAttribute("exception",e);
		}
		    
		    
		return "formEditInvoice";
	}
	
	
	@PostMapping("/updateInvoice")
	public String updateInvoice(Model model,@RequestParam(defaultValue = "0")int page,
			Invoice invoice,
			String message,
			@RequestParam("image")MultipartFile multiPartFile) throws IOException{
		
		  Invoice savedInvoice = iJournalService.getInvoiceByInvoiceId(invoice.getInvoiceId());
		
		  String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
	           			
			
		  savedInvoice.setInvoiceNumber(invoice.getInvoiceNumber());
		  savedInvoice.setCreateAt(invoice.getCreateAt());
		  savedInvoice.setAmount(invoice.getAmount());
			 
		  savedInvoice.setImageFileName(fileName);
		  
		  Invoice updatedInvoice = iJournalService.saveInvoice(savedInvoice);
		  
		  String upLoadDir = "invoice-images/" + savedInvoice.getInvoiceId();
		  
		  FileUpLoadUtil.saveFile(upLoadDir,fileName,multiPartFile);
		  
		  if(updatedInvoice != null) {
		      message = "Facture mise à jour"; 
		  }
				 
		
		return "redirect:/index?page="+page+"&message="+message;
	}
	
	
	@GetMapping("/deleteInvoice")
	public String deleteInvoice(Model model,@RequestParam(defaultValue = "0")int page,
			                    @RequestParam(name = "id") String id,
			                    String message) {
		
		iJournalService.deleteInvoice(id);
		
	    message = "Facture supprimée";
		
		
		return "redirect:/index?page="+page+"&message="+message;
	}
	
	
	@GetMapping("/monthInvoice")
	public String monthInvoice(Model model,
			@RequestParam(name = "startDate", defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(name = "endDate", defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(defaultValue = "0")int page,
			@RequestParam(defaultValue = "20")int size) {
		
		    model.addAttribute("startDate", startDate);
		    model.addAttribute("endDate", endDate);
		
        try {
			
        	if(startDate == null & endDate == null) {
        		
        		Page<Invoice> listInvoices = iJournalService.listInvoices(page, size);
    			model.addAttribute("invoices", listInvoices.getContent());
    			int[] pages = new int[listInvoices.getTotalPages()]; 
    			model.addAttribute("pages", pages);
        	}else {
        		
        		int amount = 0;
        		Page<Invoice> monthlyBills = iJournalService.monthlyInvoices(startDate, endDate,page, size);
		    	List<Invoice> monthlyInvoices = iJournalService.invoicesPerMonth(startDate, endDate);
		    	                     
		    	 
		    	for(Invoice invoice:monthlyInvoices) {
		    		amount = amount + invoice.getAmount();		  
		    	}
		    	
				model.addAttribute("invoices", monthlyBills.getContent());
				int[] pages = new int[monthlyBills.getTotalPages() - 1]; 
				model.addAttribute("pages", pages);
				model.addAttribute("amount", amount);
			
        	}
			
			model.addAttribute("currentPage", page);
			
		}catch (Exception e){
			
			model.addAttribute("exception",e);
		}
		
		
		return "monthlyInvoices";
	}
	
	
	
	@GetMapping("/invoicePerContractor")
	public String listInvoicesPerContractor(Model model,
			@RequestParam(name = "startDate", defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "endDate", defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            String name,
            @RequestParam(defaultValue = "0")int page,
			@RequestParam(defaultValue = "20")int size) {
		
		    model.addAttribute("startDate", startDate);
	        model.addAttribute("endDate", endDate);
	        model.addAttribute("name", name);
	        
	        
	        try {
	        	List<Contractor> listTenders = iJournalService.getAllTenders("CONTRACT");
			    model.addAttribute("tenders", listTenders);
	        	if(startDate == null & endDate == null) {
	        		Page<Invoice> listInvoices = iJournalService.listInvoices(page, size);
	    			model.addAttribute("invoices", listInvoices.getContent());
	    			int[] pages = new int[listInvoices.getTotalPages()]; 
	    			model.addAttribute("pages", pages);
	    			
	        	}else {
	        		List<Invoice> monthlyInvoices = iJournalService.invoicesPerMonth(startDate, endDate);
	        		List<Invoice> invoicesPerTender = monthlyInvoices.stream().filter((invoice) -> invoice.getContractor().getName().equals(name))
	        		                                                .collect(Collectors.toList());
	        		Page<Invoice> listInvoices = new PageImpl<Invoice>(invoicesPerTender,PageRequest.of(page, size), invoicesPerTender.size());
	        		model.addAttribute("invoices", listInvoices.getContent());
	    			int[] pages = new int[listInvoices.getTotalPages()]; 
	    			model.addAttribute("pages", pages);
	    			double totalAmount = invoicesPerTender.stream().mapToDouble(invoice -> invoice.getAmount()).sum();
	    			       model.addAttribute("amount", totalAmount);
	        	}
	        	
	        	model.addAttribute("currentPage", page);
	        }catch(Exception e) {
	        	model.addAttribute("exception",e);
	        }
		
		return "listInvoicesPerContractor";
	}
	
	
	
	@GetMapping("/invoicePerAllowance")
	public String natureInvoice(Model model,
                @RequestParam(name = "startDate", defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                @RequestParam(name = "endDate", defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                String allowanceName,
                @RequestParam(defaultValue = "0")int page,
    			@RequestParam(defaultValue = "20")int size) {
		
				model.addAttribute("startDate", startDate);
			    model.addAttribute("endDate", endDate);
			    model.addAttribute("allowanceName", allowanceName);
		
			    try {
					 List<Allowance> listAllowances = iJournalService.getAllAllowances();
					 model.addAttribute("allowances", listAllowances);
		        	if(startDate == null & endDate == null) {
		        		
		        		Page<Invoice> listInvoices = iJournalService.listInvoices(page, size);
		    			model.addAttribute("invoices", listInvoices.getContent());
		    			int[] pages = new int[listInvoices.getTotalPages()]; 
		    			model.addAttribute("pages", pages);
		        	}else {
		        		List<Invoice> monthlyInvoices = iJournalService.invoicesPerMonth(startDate, endDate);
		        		              monthlyInvoices.forEach(invoice -> System.out.println(invoice.getNature()));
		        		List<Invoice> invoicesPerNature = monthlyInvoices.stream()
		        				                                         .filter((invoice) -> invoice.getAllowance().getAllowanceName().equals(allowanceName))
                                                                         .collect(Collectors.toList());
		        		Page<Invoice> listInvoices = new PageImpl<Invoice>(invoicesPerNature,PageRequest.of(page, size), invoicesPerNature.size());
		        		model.addAttribute("invoices", listInvoices.getContent());
		    			int[] pages = new int[listInvoices.getTotalPages()]; 
		    			model.addAttribute("pages", pages);
		    			double totalAmount = invoicesPerNature.stream().mapToDouble(invoice -> invoice.getAmount()).sum();
		    			       model.addAttribute("amount", totalAmount);
		        	}
					
					model.addAttribute("currentPage", page);
					
				}catch (Exception e){
					
					model.addAttribute("exception",e);
				}
				
		
		return "listInvoicesPerNature";
	}
	
	
	//Controller Contractor method
	
	
	@GetMapping("/prestataires")
	public String prestataires(Model model,String type,
			String message,
			@RequestParam(name = "page", defaultValue = "0")int page,
			@RequestParam(name = "size", defaultValue = "20")int size) {
		
		           model.addAttribute("message",message);
		try {
			  
			type = "CONTRACT";
			Page<Contractor> listContractors = iJournalService.listContractors(type,page, size);
			model.addAttribute("contractors", listContractors.getContent());
			int[] pages = new int[listContractors.getTotalPages()-1];
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
	public String updateContractor(Model model,
			@RequestParam(defaultValue = "0")int page,
			Contractor contractor,
			String message) {
		 
		iJournalService.updateContractor(contractor); 
		
	    message = "Modification enregistrée...!!!";
		
		
		return "redirect:/prestataires?page="+page+"&message="+message;
	}
	
	
	
	
	//Controller Allowance methods
	
	@GetMapping("/prestations")
	public String prestations(Model model,
			@RequestParam(name = "page", defaultValue = "0")int page,
			@RequestParam(name = "size", defaultValue = "20")int size,
			String message) {
		
		    	 model.addAttribute("message", message);
		           
		try {
			  
			Page<Allowance> listAllowances = iJournalService.listAllowances(page, size);
			model.addAttribute("allowances", listAllowances.getContent());
			int[] pages = new int[listAllowances.getTotalPages()-1];
			model.addAttribute("pages", pages);			
			model.addAttribute("currentPage", page);
		
			
		}catch (Exception e) {
			
			model.addAttribute("exception",e);
		}
		
		return "indexPrestations";
	}
	
	
	@GetMapping("/editAllowance")
	public String editAllowance(Model model,Long id,
			@RequestParam(name = "page", defaultValue = "0")int page) {
		
		Allowance allowance = Director.allowanceBuilder().build();
		
        try {	
        	
		    allowance = iJournalService.getAllowanceById(id);
		    model.addAttribute("allowance", allowance);
		    model.addAttribute("page", page);
		    
		}catch(Exception e){
			  model.addAttribute("exception",e);
			  }
		
		return "editAllowance";
	}
	
	
	@PostMapping("/updateAllowance")
	public String updateAllowance(Model model,int page,
			Allowance allowance,
			String message) {
		
		 iJournalService.updateAllowance(allowance);
		 
		  message = "Modification enregistrée...!!!";
		 
		 return "redirect:/prestations?page="+page+"&message="+message;
	}
	
	
	
	
	/* Spring Security Section */
	
	@GetMapping("/newUser")
	public String formUser(Model model) {
		
		model.addAttribute("appUser", AppUser.builder().build());
			  
		return "newUser";
	}
	
	
	@PostMapping("/createUser")
	public String createUser(Model model,@Valid AppUser appUser,
			BindingResult bindingResult,
			String message) {
		
		if(bindingResult.hasErrors()) {
			
			return "newUser";
		}
		
	    customUserDetailService.addNewUser(appUser.getUsername(), appUser.getPassword(), appUser.getEmail(), appUser.getConfirmPassword());
	    
	    message = "Nouvel utilisateur enregistré avec success...!!!";
	    
		
		return "redirect:/listUsers?message="+message;
	}
	
	
	@GetMapping("/newRole")
	public String newRole(Model model) {
		
		model.addAttribute("appRole", AppRole.builder().build());
		
		List<AppRole> allRoles = customUserDetailService.listRoles();
		model.addAttribute("appRoles", allRoles);
		return "newRole";
	}
	
	
	@PostMapping("/createRole")
	public String createRole(Model model,@Valid AppRole appRole,
			BindingResult bindingResult,
			String message) {
		
		if(bindingResult.hasErrors()) {
			
			return "newRole";
		}
		
		 customUserDetailService.addNewRole(appRole.getRole());
		 
		  message = "Nouveau role enregistré avec success...!!!";
		
		return "redirect:/newRole?message="+message;
	}
	
	
	@GetMapping("/listUsers")
	public String listUsers(Model model,
			@RequestParam(name = "page", defaultValue = "0")int page,
			@RequestParam(name = "size", defaultValue = "6")int size,
			String message) {

		         model.addAttribute("message", message);
		try {
			  
			List<AppUser> users = customUserDetailService.listUsers();
			List<AppRole> roles = customUserDetailService.listRoles();
			
			model.addAttribute("users", users);
			model.addAttribute("roles", roles);
			
		}catch (Exception e) {
			
			model.addAttribute("exception",e);
		}
		
		return "listUsers";
	}
	
	
	@GetMapping("/removeRoleFromUser")
	public String removeRoleFromUser(Model model,
			@RequestParam(name = "username") String username,
			@RequestParam(name = "role") String role) {
			
		    try {
		    	
		    	 customUserDetailService.removeRoleFromUser(username, role);
		    	
		    }catch(Exception e) {
		    	
		    	model.addAttribute("exception",e);
		    }
		    
		
		return "redirect:/listUsers";
	}
	
	
	@GetMapping("/addRoleToUser")
	public String addRoleToUser(Model model,
			@RequestParam(name = "username") String username,
			@RequestParam(name = "role") String role
			) {
		
		try {
	    	
	    	   customUserDetailService.addRoleToUser(username, role);
	    	
	    }catch(Exception e) {
	    	
	    	   model.addAttribute("exception",e);
	    }
		
		return "redirect:/listUsers";
	}
	
	
	
	  @GetMapping("/profile") 
	  public String profile(Model model) {
	  
	       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	       AppUser appUser = appRoleServiceImpl.loadUserByUsername(auth.getName());
	       model.addAttribute("user", appUser);
	  
	     return "profile"; 
	  }
	  
	  
	  @PostMapping("/updateProfile")
	  public String updateProfile(AppUser appUser) {
		  
		 customUserDetailService.updateUser(appUser);
		  
		  return"redirect:/login";
	  }
	 
	
}

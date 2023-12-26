package com.ogivesas.journal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogivesas.journal.services.JournalService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class JournalController {

	private JournalService iJournalService;
	
	
	@GetMapping("/index")
	public String index(Model model) {
		
		
		return "homePage";
	}
	
	
	@GetMapping("/prestataires")
	public String prestataires(Model model) {
		
		
		return "indexPrestataires";
	}
	
	
	@GetMapping("/prestations")
	public String prestations(Model model) {
		
		
		return "indexPrestations";
	}
	
	
	@GetMapping("/formInvoice")
	public String register(Model model) {
		
		
		return "formInvoice";
	}
	
	
	@GetMapping("/updateInvoice")
	public String modify(Model model) {
		
		
		return "updateInvoice";
	}
	
}

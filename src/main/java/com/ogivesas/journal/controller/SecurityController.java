package com.ogivesas.journal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping("/login")
	public String showLogin() {
			
	   return "login";
	}
	
	
	@GetMapping("/")
	public String homePage() {
		
		return "redirect:/index";
	}
	
	
	@GetMapping("/403")
	public String notAuthorized() {
		
		return "403";
	}
}

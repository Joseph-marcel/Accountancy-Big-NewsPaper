package com.ogivesas.journal.models;


public class Director {

    public static Customer.CompanyBuilder customerBuilder(){
		
		return new Customer.CompanyBuilder();
	}

   public static Contractor.CompanyBuilder contractorBuilder(){
	
	    return new Contractor.CompanyBuilder();
    }
   
   public static Allowance.AllowanceBuilder allowanceBuilder(){
	   
	   return new Allowance.AllowanceBuilder();
   }
   
   public static Invoice.InvoiceBuilder invoiceBuilder(){
	   
	   return new Invoice.InvoiceBuilder();
   }
}

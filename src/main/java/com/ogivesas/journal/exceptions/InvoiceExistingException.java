package com.ogivesas.journal.exceptions;


public class InvoiceExistingException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvoiceExistingException(String s) {
		super(s);
	}
}

package com.ogivesas.journal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)

public class InvoiceExistingException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvoiceExistingException(String s) {
		super(s);
	}
}

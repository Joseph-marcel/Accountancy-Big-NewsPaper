package com.ogivesas.journal.exceptions;

public class ContractorNotExistingException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ContractorNotExistingException(String s) {
		super(s);
	}
}

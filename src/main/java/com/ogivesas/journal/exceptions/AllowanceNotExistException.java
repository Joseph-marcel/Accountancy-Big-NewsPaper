package com.ogivesas.journal.exceptions;

public class AllowanceNotExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AllowanceNotExistException(String s) {
		super(s);
	}
}

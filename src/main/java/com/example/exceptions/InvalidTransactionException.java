package com.example.exceptions;

public class InvalidTransactionException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidTransactionException() {
		super("Amount attepmt is invalid, $0 below accounts dont exist");
	}

}

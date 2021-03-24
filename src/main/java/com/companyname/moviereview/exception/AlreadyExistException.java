package com.companyname.moviereview.exception;

public class AlreadyExistException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8656010064135851966L;

	public AlreadyExistException() {
		super("User creation failed. User Already Exists.");
	}
	
	public AlreadyExistException(String message) {
		super(message);
	}
}

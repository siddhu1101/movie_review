package com.companyname.moviereview.exception;

public class NotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5844652314524886329L;

	public NotFoundException() {
		super("User creation failed. User Already Exists.");
	}
	
	public NotFoundException(String message) {
		super(message);
	}
}

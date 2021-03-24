package com.companyname.moviereview.model;

import java.util.HashMap;

public class UserDetails {
	private String userId;
	private String name;
	private int moviesReviewed;
	private String designation;
	private HashMap<String, ReviewDetails> reviews;
	
	public HashMap<String, ReviewDetails> getReviews() {
		return reviews;
	}
	public void setReviews(HashMap<String, ReviewDetails> reviews) {
		this.reviews = reviews;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoviesReviewed() {
		return moviesReviewed;
	}
	public void setMoviesReviewed(int moviesReviewed) {
		this.moviesReviewed = moviesReviewed;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}

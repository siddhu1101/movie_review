package com.companyname.moviereview.iservice;

import java.util.List;
import java.util.Map;

import com.companyname.moviereview.exception.AlreadyExistException;
import com.companyname.moviereview.exception.NotFoundException;
import com.companyname.moviereview.model.MovieDetails;
import com.companyname.moviereview.model.ReviewDetails;
import com.companyname.moviereview.model.UserDetails;

public interface IMoviewreviewService {
	public UserDetails addUser(UserDetails user) throws AlreadyExistException;
	
	public UserDetails getUser(String userId) throws NotFoundException;
	
	public MovieDetails addMovie(MovieDetails movie) throws AlreadyExistException;
	
	public MovieDetails getMovie(String movieName) throws NotFoundException;
	
	public UserDetails addReview(ReviewDetails review) throws AlreadyExistException, RuntimeException;
	
	public List<Map.Entry<String, Float>> getMoviesByGenreAndCriticReview(String genre, int numberOfMovies) throws Exception;
	
	public float getAverageReviewByReleaseYear(int year);
	
	public float getAverageReviewForMovie(String movieName);
}

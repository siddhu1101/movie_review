package com.companyname.moviereview.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.companyname.moviereview.exception.AlreadyExistException;
import com.companyname.moviereview.exception.NotFoundException;
import com.companyname.moviereview.iservice.IMoviewreviewService;
import com.companyname.moviereview.model.MovieDetails;
import com.companyname.moviereview.model.ReviewDetails;
import com.companyname.moviereview.model.UserDetails;

@RestController
public class MoviereviewRestController {
	@Autowired
	private IMoviewreviewService reviewService;

	@PostMapping("/user")
	public UserDetails addUser(@RequestBody UserDetails user) throws AlreadyExistException {
		return reviewService.addUser(user);
	}

	@GetMapping("/user/{userId}")
	public UserDetails getUser(@PathVariable("userId") String userId) throws NotFoundException {
		return reviewService.getUser(userId);
	}

	@PostMapping("/movie")
	public MovieDetails addMovie(@RequestBody MovieDetails movie) throws AlreadyExistException {
		return reviewService.addMovie(movie);
	}

	@GetMapping("/movie/{movieName}")
	public MovieDetails getMovie(@PathVariable("movieName") String movieName) throws NotFoundException {
		return reviewService.getMovie(movieName);
	}

	@PostMapping("/review")
	public UserDetails addReview(@RequestBody ReviewDetails review) throws AlreadyExistException, RuntimeException {
		return reviewService.addReview(review);
	}

	@GetMapping("/movies/critic/{genre}/{numberOfMovies}")
	public List<Map.Entry<String, Float>> getMoviesByGenreAndDeisgnation(@PathVariable("numberOfMovies") int numberOfMovies,
			@PathVariable("genre") String genre) throws Exception {
		return reviewService.getMoviesByGenreAndCriticReview(genre, numberOfMovies);
	}
	
	@GetMapping("/review/average/year/{year}")
	public float getAverageReviewByYear(@PathVariable("year") int year) {
		return reviewService.getAverageReviewByReleaseYear(year);
	}
	
	@GetMapping("/review/average/movie/{movieName}")
	public float getAverageReviewByMovieName(@PathVariable("movieName") String movieName) {
		return reviewService.getAverageReviewForMovie(movieName);
	}
}

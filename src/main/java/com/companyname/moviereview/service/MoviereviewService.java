package com.companyname.moviereview.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.companyname.moviereview.exception.AlreadyExistException;
import com.companyname.moviereview.exception.NotFoundException;
import com.companyname.moviereview.iservice.IMoviewreviewService;
import com.companyname.moviereview.model.MovieDetails;
import com.companyname.moviereview.model.ReviewDetails;
import com.companyname.moviereview.model.UserDetails;

@Service
public class MoviereviewService implements IMoviewreviewService {
	HashMap<String, UserDetails> users = new HashMap<>();
	HashMap<String, MovieDetails> movies = new HashMap<>();
	HashMap<String, ReviewDetails> review = new HashMap<>();

	@Override
	public UserDetails addUser(UserDetails user) throws AlreadyExistException {
		if (users.containsKey(user.getUserId())) {
			throw new AlreadyExistException("User already Exists...");
		}
		UserDetails newUser = user;
		users.put(newUser.getUserId(), newUser);
		return newUser;
	}

	@Override
	public UserDetails getUser(String userId) throws NotFoundException {
		if (!users.containsKey(userId)) {
			throw new NotFoundException("User Not Found...");
		}
		return users.get(userId);
	}

	@Override
	public MovieDetails addMovie(MovieDetails movie) throws AlreadyExistException {
		if (users.containsKey(movie.getMovieName())) {
			throw new AlreadyExistException();
		}
		MovieDetails newMovie = movie;
		movies.put(newMovie.getMovieName(), newMovie);
		return newMovie;
	}

	@Override
	public MovieDetails getMovie(String movieName) throws NotFoundException {
		try {
			return movies.get(movieName);
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

	@Override
	public UserDetails addReview(ReviewDetails review) throws AlreadyExistException, RuntimeException {
		if (review.getRating() > 10 || review.getRating() < 0) {
			throw new RuntimeException("Rating value should be between 0 and 10...");
		}
		MovieDetails movie = movies.get(review.getMovieName());
		if (movie == null) {
			throw new RuntimeException("Cannot find the movie. Cannot Review...");
		}
		if (!movie.isReleased()) {
			throw new RuntimeException("Movie has not been released yet. Cannot Review...");
		}
		UserDetails user = users.get(review.getUserId());
		if (user.getReviews().containsKey(review.getMovieName())) {
			throw new AlreadyExistException("Cannot review one movie more than once...");
		}
		user.getReviews().put(review.getMovieName(), review);
		user.setMoviesReviewed(user.getMoviesReviewed() + 1);
		if (user.getMoviesReviewed() >= 3) {
			user.setDesignation("critic");
		}
		users.put(review.getUserId(), user);
		return user;
	}

	@Override
	public List<Map.Entry<String, Float>> getMoviesByGenreAndCriticReview(String genre, int numberOfMovies)
			throws Exception {
		HashMap<String, Float> movieList = new HashMap<>();
		for (Map.Entry<String, MovieDetails> element : movies.entrySet()) {
			MovieDetails movie = element.getValue();
			int ratingSum = 0;
			int numberOfRatings = 0;
			float average = 0;
			if (movie.getGenre().equals(genre)) {
				for (Map.Entry<String, UserDetails> userElement : users.entrySet()) {
					if (userElement.getValue().getDesignation().equals("critic")) {
						UserDetails user = userElement.getValue();
						if (user.getReviews().get(movie.getMovieName()) != null) {
							ratingSum += user.getReviews().get(movie.getMovieName()).getRating();
							numberOfRatings += 1;
						}
					}
				}
				average = ratingSum / numberOfRatings;
				movieList.put(movie.getMovieName(), average);
			}
		}
		List<Map.Entry<String, Float>> list = new ArrayList<Map.Entry<String, Float>>(movieList.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Float>>() {
			public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
		while (list.size() > numberOfMovies) {
			list.remove(0);
		}
		return list;
	}

	@Override
	public float getAverageReviewByReleaseYear(int year) {
		float average = 0;
		int ratingSum = 0;
		int numberOfRatings = 0;
		for (Map.Entry<String, MovieDetails> movieElement : movies.entrySet()) {
			MovieDetails movie = movieElement.getValue();
			if (movie.getReleaseYear() == year) {
				for (Map.Entry<String, UserDetails> userElement : users.entrySet()) {
					UserDetails user = userElement.getValue();
					if (user.getReviews().get(movie.getMovieName()) != null) {
						ratingSum += user.getReviews().get(movie.getMovieName()).getRating();
						numberOfRatings += 1;
					}
				}
			}
		}
		average = ratingSum/numberOfRatings;
		return average;
	}

	@Override
	public float getAverageReviewForMovie(String movieName) {
		float average = 0;
		int ratingSum = 0;
		int numberOfRatings = 0;
		for (Map.Entry<String, MovieDetails> movieElement : movies.entrySet()) {
			MovieDetails movie = movieElement.getValue();
			if (movie.getMovieName().equals(movieName)) {
				for (Map.Entry<String, UserDetails> userElement : users.entrySet()) {
					UserDetails user = userElement.getValue();
					if (user.getReviews().get(movie.getMovieName()) != null) {
						ratingSum += user.getReviews().get(movie.getMovieName()).getRating();
						numberOfRatings += 1;
					}
				}
			}
		}
		average = ratingSum/numberOfRatings;
		return average;
	}

}

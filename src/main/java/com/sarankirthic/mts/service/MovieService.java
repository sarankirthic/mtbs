package com.sarankirthic.mts.service;

import com.sarankirthic.mts.model.Movie;
import com.sarankirthic.mts.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
    	return movieRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
    
    public long getTotalMoviesCount() {
        return movieRepository.count();
    }
    
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }
    
    public List<Movie> getNowShowing() {
        return movieRepository.findNowShowing();
    }

    public List<Movie> getUpcomingMovies() {
        return movieRepository.findUpcomingMovies();
    }

    public List<Movie> getNewReleases() {
        return movieRepository.findLatestMovies();
    }

    public List<Movie> getTop10ByShowPrice() {
        return movieRepository.findTop10MoviesByHighestShowPrice();
    }
}
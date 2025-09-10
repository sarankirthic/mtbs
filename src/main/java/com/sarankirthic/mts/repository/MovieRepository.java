package com.sarankirthic.mts.repository;

import com.sarankirthic.mts.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	List<Movie> findByReleaseDateBetween(LocalDate start, LocalDate end);
	
    List<Movie> findByGenre(String genre);
    
    List<Movie> findByReleaseDateBefore(LocalDate date); // For now showing
    
    List<Movie> findByReleaseDateAfter(LocalDate date); // For coming soon
    
    @Query("SELECT m FROM Movie m WHERE m.releaseDate >= :date")
    List<Movie> getNewReleases(@Param("date") LocalDate date);
    
    
    //new
    @Query("SELECT m FROM Movie m WHERE m.releaseDate <= CURRENT_DATE ORDER BY m.releaseDate DESC")
    List<Movie> findNowShowing();

    @Query("SELECT m FROM Movie m WHERE m.releaseDate > CURRENT_DATE ORDER BY m.releaseDate ASC")
    List<Movie> findUpcomingMovies();

    @Query("SELECT m FROM Movie m ORDER BY m.releaseDate DESC LIMIT 10")
    List<Movie> findLatestMovies();

    @Query("SELECT s.movie FROM Show s GROUP BY s.movie ORDER BY MAX(s.price) DESC LIMIT 10")
    List<Movie> findTop10MoviesByHighestShowPrice();
	
}

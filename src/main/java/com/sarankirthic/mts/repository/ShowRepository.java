package com.sarankirthic.mts.repository;

import com.sarankirthic.mts.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {
	
    List<Show> findByMovieId(Long movieId);
    
    List<Show> findTop10ByOrderByPriceDesc();
    
    @Query("SELECT s FROM Show s WHERE LOWER(s.movie.genre) = LOWER(:genre)")
    List<Show> findShowsByMovieGenre(@Param("genre") String genre);

}
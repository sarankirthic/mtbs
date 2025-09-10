package com.sarankirthic.mts.controller;

import com.sarankirthic.mts.model.Show;
import com.sarankirthic.mts.service.BookingService;
import com.sarankirthic.mts.service.MovieService;
import com.sarankirthic.mts.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ShowController {

    @Autowired
    private ShowService showService;

    @Autowired
    private MovieService movieService;
    
    @Autowired
    private BookingService bookingService;

    @GetMapping("/shows")
    public String listShows(Model model) {
    	List<Show> shows = showService.getAllShows();
    	shows.sort((b1, b2) -> b2.getId().compareTo(b1.getId()));
        model.addAttribute("shows", shows);
        return "admin/shows";
    }

    @GetMapping("/shows/add")
    public String addShowForm(Model model) {
        model.addAttribute("show", new Show());
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin/add-show";
    }

    @PostMapping("/shows/save")
    public String saveShow(@ModelAttribute Show show) {
        showService.saveShow(show);
        return "redirect:/admin/shows";
    }

    @GetMapping("/shows/edit/{id}")
    public String editShow(@PathVariable Long id, Model model) {
        model.addAttribute("show", showService.getShowById(id));
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin/add-show";
    }

    @GetMapping("/shows/delete/{id}")
    public String deleteShow(@PathVariable Long id) {
    	bookingService.deleteByShowId(id);
        showService.deleteShow(id);
        return "redirect:/admin/shows";
    }
    
    @GetMapping("/user/shows/{showId}/occupied-seats")
    public ResponseEntity<List<String>> getOccupiedSeatsForShow(@PathVariable Long showId) {
        Show show = showService.getShowById(showId);
        if (show == null) {
            return ResponseEntity.notFound().build();
        }
        List<String> occupiedSeats = bookingService.getOccupiedSeats(show);
        return ResponseEntity.ok(occupiedSeats);
    }
    
    
    
}
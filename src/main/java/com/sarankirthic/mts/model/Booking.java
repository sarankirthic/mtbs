package com.sarankirthic.mts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Booking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Show show;

    private String seatNumbers;

    private double totalAmount;
    
    @Column(nullable = false)
    private LocalDateTime bookingTime;
    
    @PrePersist
    protected void onCreate() {
        bookingTime = LocalDateTime.now();
    }
}

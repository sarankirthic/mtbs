package com.sarankirthic.mts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Movie {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String genre;

    private String language;
    
    private LocalDate releaseDate;

    @Lob
	@Basic(fetch = FetchType.LAZY)
    private byte[] posterData;
	
	@Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] backgroundData;

	private int duration;

}

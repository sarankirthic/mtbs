package com.sarankirthic.mts.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
	private Long showId;
	private String seatNumbers;
	private int seatCount;

}
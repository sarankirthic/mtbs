package com.sarankirthic.mts.controller;

import com.sarankirthic.mts.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/mail")
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody Map<String, String> payload) {
    String email = payload.get("email");

    if (email == null || email.isEmpty()) {
    return ResponseEntity.badRequest().body("Email is missing");
    }

    try {
            emailService.sendEmail(
            email,
            "You're in! Account created successfully!",
            "Welcome to Book My Movie — your gateway to the ultimate movie experience. Let the show begin ... Thank you!"
            );
            return ResponseEntity.ok("Email sent to " + email);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Failed to send email");
        }
    }
    
    @PostMapping("/sendmailafterpay")
    public ResponseEntity<String> sendEmailAfterPayment(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String bookingIdStr = payload.get("bookingId");

        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email is missing");
        }

        if (bookingIdStr == null || bookingIdStr.isEmpty()) {
            return ResponseEntity.badRequest().body("Booking ID is missing");
        }

        try {
            Long bookingId = Long.parseLong(bookingIdStr);
            emailService.sendBookingConfirmationEmail(bookingId, email);
            return ResponseEntity.ok("Confirmation email with PDF ticket sent to " + email);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid Booking ID format");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Failed to send email");
        }
    }

 
}


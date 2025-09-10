package com.sarankirthic.mts.controller;

import com.sarankirthic.mts.model.Transaction;
import com.sarankirthic.mts.service.orderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class paymentTransactionController 
{
	@Autowired
	private orderServices orderservices;
	@CrossOrigin(origins = "*")
	@GetMapping("/getTransaction/{amount}")
	public Transaction getTransaction(@PathVariable double amount) {
        Transaction transactionDetails = orderservices.orderCreateTransaction(amount);
        if (transactionDetails != null) {
            return transactionDetails;
        } else {
            // Handle error case
            return null;  // Return an error response, or you could throw a custom exception
        }
    }
	
	
	
	
	
	
	
	
	

}

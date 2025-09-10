package com.sarankirthic.mts.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Transaction {
    private String orderId;
    private String currency;
    private int amount;
    private String key;

    public Transaction(String orderId, String currency, int amount, String key) {
        this.orderId = orderId;
        this.currency = currency;
        this.amount = amount;
        this.key = key;
    }
}

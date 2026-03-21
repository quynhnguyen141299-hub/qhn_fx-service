package com.quynh.fxservice.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class ConversionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromCurrency;
    private String toCurrency;
    private double amount;
    private double result;
    private Instant requestedAt;

    public ConversionRequest() {}

    public ConversionRequest(String fromCurrency, String toCurrency,
                             double amount, double result, Instant requestedAt) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.result = result;
        this.requestedAt = requestedAt;
    }

    // getters and setters
    public Long getId() { return id; }
    public String getFromCurrency() { return fromCurrency; }
    public void setFromCurrency(String fromCurrency) { this.fromCurrency = fromCurrency; }
    public String getToCurrency() { return toCurrency; }
    public void setToCurrency(String toCurrency) { this.toCurrency = toCurrency; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public double getResult() { return result; }
    public void setResult(double result) { this.result = result; }
    public Instant getRequestedAt() { return requestedAt; }
    public void setRequestedAt(Instant requestedAt) { this.requestedAt = requestedAt; }
}
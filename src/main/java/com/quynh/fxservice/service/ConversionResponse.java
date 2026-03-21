package com.quynh.fxservice.service;

public record ConversionResponse(
        String from,
        String to,
        double amount,
        double result
) {}
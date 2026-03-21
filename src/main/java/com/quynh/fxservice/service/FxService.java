package com.quynh.fxservice.service;

import com.quynh.fxservice.domain.ConversionRequest;
import com.quynh.fxservice.domain.FxRate;
import com.quynh.fxservice.fx.FxRateClient;
import com.quynh.fxservice.repo.ConversionRequestRepository;
import com.quynh.fxservice.repo.FxRateRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
public class FxService {

    private final FxRateClient client;
    private final FxRateRepository rateRepo;
    private final ConversionRequestRepository convRepo;

    public FxService(FxRateClient client,
                     FxRateRepository rateRepo,
                     ConversionRequestRepository convRepo) {
        this.client = client;
        this.rateRepo = rateRepo;
        this.convRepo = convRepo;
    }

    public Map<String, Double> getAndStoreRates(String base) {
        final String baseCcy = base.toUpperCase();
        Map<String, Double> rates = client.getLatestRates(baseCcy);
        Instant now = Instant.now();
        rates.forEach((quote, rate) -> {
            FxRate fxRate = new FxRate(baseCcy, quote, rate, now);
            rateRepo.save(fxRate);
        });
        return rates;
    }

    public ConversionResponse convertAndStore(String from, String to, double amount) {
        final String fromCcy = from.toUpperCase();
        final String toCcy = to.toUpperCase();
        double result = client.convert(fromCcy, toCcy, amount);
        ConversionRequest conv = new ConversionRequest(
                fromCcy, toCcy, amount, result, Instant.now()
        );
        convRepo.save(conv);
        return new ConversionResponse(fromCcy, toCcy, amount, result);
    }
}
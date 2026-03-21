package com.quynh.fxservice.fx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class FxRateClient {

    private static final Logger log = LoggerFactory.getLogger(FxRateClient.class);

    @Value("${fx.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @SuppressWarnings("unchecked")
    public Map<String, Double> getLatestRates(String base) {
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + base;
        log.info("Calling FX API: {}", url);

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response == null || !"success".equals(response.get("result"))) {
            log.error("FX API call failed: {}", response);
            throw new IllegalStateException("Failed to fetch FX rates");
        }

        Map<String, Object> rawRates = (Map<String, Object>) response.get("conversion_rates");
        Map<String, Double> rates = new HashMap<>();
        for (Map.Entry<String, Object> entry : rawRates.entrySet()) {
            rates.put(entry.getKey(), ((Number) entry.getValue()).doubleValue());
        }

        log.info("Received {} rates for base {}", rates.size(), base);
        return rates;
    }

    public double convert(String from, String to, double amount) {
        Map<String, Double> rates = getLatestRates(from);
        Double rate = rates.get(to);
        if (rate == null) {
            throw new IllegalArgumentException("Unsupported target currency: " + to);
        }
        return amount * rate;
    }
}
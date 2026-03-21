package com.quynh.fxservice.api;

import com.quynh.fxservice.service.ConversionResponse;
import com.quynh.fxservice.service.FxService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class FxController {

    private final FxService fxService;

    public FxController(FxService fxService) {
        this.fxService = fxService;
    }

    @GetMapping("/rates")
    public Map<String, Double> getRates(@RequestParam String base) {
        return fxService.getAndStoreRates(base);
    }

    @GetMapping("/convert")
    public ConversionResponse convert(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount) {
        return fxService.convertAndStore(from, to, amount);
    }
}
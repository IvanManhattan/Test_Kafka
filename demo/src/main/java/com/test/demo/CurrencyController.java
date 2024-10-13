package com.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/api/currencies")
    public List<Currency> getCurrencies() {
        return currencyService.getLatestRates();
    }
    @GetMapping("/api/currencyHistory")
    public List<CurrencyRateHistory> getCurrencyHistory() {
        return currencyService.getCurrencyHistory(431, "2024-09-20", "2024-09-30");
    }
}

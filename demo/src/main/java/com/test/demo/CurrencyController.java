package com.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CurrencyController {
    private final RequestProducerService requestProducerService;
    @Autowired
    private CurrencyService currencyService;

    public CurrencyController(RequestProducerService requestProducerService) {
        this.requestProducerService = requestProducerService;
    }

    @GetMapping("/sendRequest")
    public String sendRequest() {
        String apiUrl = "https://api.nbrb.by/exrates/rates?periodicity=0";
        requestProducerService.sendRequest(apiUrl);
        return "Request with URL sent to Kafka: " + apiUrl;
    }
    /*@GetMapping("/api/currencyHistory")
    public List<CurrencyRateHistory> getCurrencyHistory() {
        return currencyService.getCurrencyHistory(431, "2024-09-20", "2024-09-30");
    }*/
}

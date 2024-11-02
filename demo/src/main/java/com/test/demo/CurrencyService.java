package com.test.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CurrencyService {

    private final String EXCHANGE_RATE_API_URL = "https://www.cbr-xml-daily.ru/daily_json.js";
    private final String EXCHANGE_RATE_API_URL_2 = "https://api.nbrb.by/exrates/rates?periodicity=0";
    // https://api.nbrb.by/exrates/rates?periodicity=0
    // https://api.nbrb.by/exrates/rates/dynamics/USD?parammode=2
    // https://api.nbrb.by/ExRates/Rates/Dynamics/456?startDate=2024-07-09&endDate=2024-07-31 - динамика российского рубля за период
    // https://api.nbrb.by/ExRates/Rates/Dynamics/431?startDate=2024-07-09&endDate=2024-08-09
    private List<Currency> currencies;
    private List<CurrencyRateHistory> currencyRateHistory;

    public List<Currency> getLatestRates(String apiUrl) {
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);  // Используем переданный URL
        System.out.println("API Response: " + jsonResponse);
        currencies = new ArrayList<>();

        if (jsonResponse != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                // JSON mapping into Currency objects
                currencies = objectMapper.readValue(jsonResponse, new TypeReference<List<Currency>>() {});
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error parsing JSON: " + e.getMessage());
            }
        } else {
            System.out.println("No data");
        }

        // Show all currencies
        currencies.forEach(System.out::println);
        return currencies;
    }

    public List<CurrencyRateHistory> getCurrencyHistory(int currId, String startDate, String endDate) {
        RestTemplate restTemplate = new RestTemplate();
        String currencyHistoryAPI =
                "https://api.nbrb.by/ExRates/Rates/Dynamics/" + currId + "?startDate=" + startDate + "&endDate=" + endDate;
        String jsonResponse = restTemplate.getForObject(currencyHistoryAPI, String.class);
        currencyRateHistory = new ArrayList<>();

        if (jsonResponse != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();

                // JSON mapping into Currency objects
                currencyRateHistory = objectMapper.readValue(jsonResponse, new TypeReference<List<CurrencyRateHistory>>() {});

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error parsing JSON: " + e.getMessage());
            }
        } else {
            System.out.println("No data");
        }

        // Show all currencies
        currencyRateHistory.forEach(System.out::println);

        return currencyRateHistory;
    }
}

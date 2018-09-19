package com.example.websocketRateGetter.config;

public class UrlBuilder {

    String baseUrl = "https://bittrex.com/api/v1.1/public/getmarketsummary";

    public String buildUrl(String exchangeName, String currencyFrom, String currencyTo) {
        return baseUrl + "?market=" + currencyFrom.toLowerCase() + "-" + currencyTo.toLowerCase();
    };
}

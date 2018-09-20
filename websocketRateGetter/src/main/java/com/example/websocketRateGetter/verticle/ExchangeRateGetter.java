package com.example.websocketRateGetter.verticle;

import com.example.websocketRateGetter.repositories.RateRepository;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.shareddata.SharedData;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.marketdata.MarketDataService;

public abstract class ExchangeRateGetter extends AbstractVerticle {

    Exchange exchangeInstance;
    MarketDataService marketDataService;

    public ExchangeRateGetter() {
        exchangeInstance = getInstance();
        marketDataService = exchangeInstance.getMarketDataService();
    }

    public void saveToRepo(Double rate) {
        SharedData data = vertx.sharedData();
        RateRepository rateRepository = new RateRepository(data);
        rateRepository.update(exchangeInstance.getClass().getName(), rate);
    }

    abstract Exchange getInstance();

}

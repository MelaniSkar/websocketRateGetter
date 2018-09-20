package com.example.websocketRateGetter.verticle;

import com.example.websocketRateGetter.eventBus.Addresses;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitfinex.v2.service.BitfinexMarketDataServiceRaw;
import org.knowm.xchange.bitfinex.v2.BitfinexExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BitfinexRateGetter extends ExchangeRateGetter {

    @Override
    public void start() {
        vertx.eventBus().consumer(Addresses.BITFINEX_GET_RATE, msg -> {
            CurrencyPair currencyPair = new CurrencyPair(msg.body().toString());
            try {
                Double rate = ((BitfinexMarketDataServiceRaw)marketDataService)
                        .getBitfinexTicker(currencyPair).getLastPrice().doubleValue();
                saveToRepo(rate);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    Exchange getInstance() {
        return ExchangeFactory.INSTANCE.createExchange(BitfinexExchange.class.getName());
    }
}

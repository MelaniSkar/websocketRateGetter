package com.example.websocketRateGetter.verticle;

import com.example.websocketRateGetter.eventBus.Addresses;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bittrex.BittrexExchange;
import org.knowm.xchange.bittrex.service.BittrexMarketDataServiceRaw;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BittrexRateGetter extends ExchangeRateGetter {

    @Override
    public void start() {
        vertx.eventBus().consumer(Addresses.BITTREX_GET_RATE, msg -> {
            CurrencyPair currencyPair = new CurrencyPair(msg.body().toString());
            try {
                Double rate = ((BittrexMarketDataServiceRaw)marketDataService)
                        .getBittrexTicker(currencyPair).getLast().doubleValue();
                saveToRepo(rate);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    Exchange getInstance() {
        return ExchangeFactory.INSTANCE.createExchange(BittrexExchange.class.getName());
    }

}

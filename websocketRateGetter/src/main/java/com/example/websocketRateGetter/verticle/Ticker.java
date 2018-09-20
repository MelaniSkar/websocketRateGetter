package com.example.websocketRateGetter.verticle;

import com.example.websocketRateGetter.eventBus.Addresses;
import io.vertx.core.AbstractVerticle;
import org.springframework.stereotype.Component;


@Component
public class Ticker extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.setPeriodic(5000, event -> {
            String message = "BTC/USD";
            vertx.eventBus().publish(Addresses.BITFINEX_GET_RATE, message);
            vertx.eventBus().publish(Addresses.BITTREX_GET_RATE, message);
        });
    }
}

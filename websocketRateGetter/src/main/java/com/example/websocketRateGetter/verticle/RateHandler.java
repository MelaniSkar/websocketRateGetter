package com.example.websocketRateGetter.verticle;

import com.example.websocketRateGetter.eventBus.Addresses;
import com.example.websocketRateGetter.repositories.RateRepository;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.bridge.BridgeEventType;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;
import org.knowm.xchange.bitfinex.v2.Bitfinex;
import org.knowm.xchange.bitfinex.v2.BitfinexExchange;
import org.knowm.xchange.bittrex.BittrexExchange;

import java.math.BigDecimal;
import java.util.Optional;

public class RateHandler implements Handler<BridgeEvent> {


    private static final Logger logger = LoggerFactory.getLogger(RateHandler.class);
    private final EventBus eventBus;
    private final RateRepository rateRepository;

    RateHandler(EventBus eventBus, RateRepository rateRepository) {
        this.eventBus = eventBus;
        this.rateRepository = rateRepository;
    }

    @Override
    public void handle(BridgeEvent event) {
        if (event.type() == BridgeEventType.SOCKET_PING) {
            Optional<Double> bitfinexRate = rateRepository.get(BitfinexExchange.class.getName());
            Optional<Double> bittrexRate = rateRepository.get(BittrexExchange.class.getName());
            eventBus.publish(Addresses.PUBLISH_BITTREX_RATE, bittrexRate.get().toString());
            eventBus.publish(Addresses.PUBLISH_BITFINEX_RATE, bitfinexRate.get().toString());
        }

        event.complete(true);
    }
}

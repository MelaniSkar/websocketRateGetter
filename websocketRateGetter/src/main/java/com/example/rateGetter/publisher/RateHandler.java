package com.example.rateGetter.publisher;

import com.example.rateGetter.repositories.RateRepository;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.bridge.BridgeEventType;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;

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
            Optional<Double> rate = rateRepository.get();
            eventBus.publish("out", rate.get());
        }

        event.complete(true);
    }
}

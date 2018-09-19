package com.example.websocketRateGetter.publisher;

import com.example.websocketRateGetter.repositories.RateRepository;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.springframework.stereotype.Component;

@Component
public class WebSocketServer extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);

        router.route("/eventbus/*").handler(getRateHandler());
        router.route().handler(staticHandler());

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);
    }

    private SockJSHandler getRateHandler() {
        BridgeOptions options = new BridgeOptions()
                .addOutboundPermitted(new PermittedOptions().setAddressRegex("out"));
        SharedData data = vertx.sharedData();
        RateRepository rateRepository = new RateRepository(data);
        EventBus eventBus = vertx.eventBus();
        RateHandler rateHandler = new RateHandler(eventBus, rateRepository);
        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        return sockJSHandler.bridge(options, rateHandler);

    }

    private StaticHandler staticHandler() {
        return StaticHandler.create()
                .setCachingEnabled(false);
    }
}

package com.example.websocketRateGetter.listener;

import com.example.websocketRateGetter.config.URLS;
import com.example.websocketRateGetter.eventBus.Addresses;
import com.example.websocketRateGetter.repositories.RateRepository;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import org.springframework.stereotype.Component;

@Component
public class TickerListener extends AbstractVerticle {

    @Override
    public void start() {
        vertx.eventBus().consumer(Addresses.MAKE_REQUEST_ADDRESS, message -> {
            WebClient client = WebClient.create(vertx);
            client
                    .getAbs(URLS.BITTREX) //creates Http GET request
                    .as(BodyCodec.jsonObject())
                    .send(handleResponse());
        });
    }

    public Handler<AsyncResult<HttpResponse<JsonObject>>> handleResponse() {
        return ar -> {
            if (ar.succeeded()) {
                Double rate = ar.result().body()
                        .getJsonArray("result").getJsonObject(0).getDouble("Last");
                SharedData data = vertx.sharedData();
                RateRepository rateRepository = new RateRepository(data);
                rateRepository.update(rate);
            } else {
                System.out.println(ar.cause());
            }
        };
    }
}

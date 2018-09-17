package com.example.rateGetter.publisher;

import com.example.rateGetter.eventBus.Addresses;
import io.vertx.core.AbstractVerticle;
import org.springframework.stereotype.Component;


@Component
public class Ticker extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    vertx.setPeriodic(10000, event -> {
      vertx.eventBus().publish(Addresses.MAKE_REQUEST_ADDRESS,
        "bittrex"+ "BTC"+ "USD");
    });
  }

  public void doS() {
    System.out.println("h");
  }
}

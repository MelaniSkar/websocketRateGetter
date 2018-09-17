package com.example.rateGetter;


import com.example.rateGetter.listener.TickerListener;
import com.example.rateGetter.publisher.Ticker;
import com.example.rateGetter.publisher.WebSocketServer;
import io.vertx.core.Vertx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @PostConstruct
  public void deployVerticles() {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(WebSocketServer.class.getName());
    vertx.deployVerticle(TickerListener.class.getName());
    vertx.deployVerticle(Ticker.class.getName());
  }
}

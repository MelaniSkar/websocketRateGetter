package com.example.rateGetter;


import com.example.rateGetter.verticle.TickerListener;
import com.example.rateGetter.verticle.Ticker;
import com.example.rateGetter.verticle.WebSocketServer;
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
    vertx.deployVerticle(Ticker.class.getName());
    vertx.deployVerticle(TickerListener.class.getName());
    vertx.deployVerticle(WebSocketServer.class.getName());


  }
}

package com.example.rateGetter.eventBus;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import jdk.nashorn.internal.parser.JSONParser;

import java.util.HashMap;
import java.util.Map;

public class TickerMessage implements Message<JsonObject> {
  String exchange;
  String currencyFrom;
  String currencyTo;

  public String getExchange() {
    return exchange;
  }

  public String getCurrencyFrom() {
    return currencyFrom;
  }

  public String getCurrencyTo() {
    return currencyTo;
  }

  public TickerMessage(String exchange, String currencyFrom, String currencyTo) {
    this.exchange = exchange;
    this.currencyFrom = currencyFrom;
    this.currencyTo = currencyTo;
  }

  @Override
  public String address() {
    return null;
  }

  @Override
  public MultiMap headers() {
    return null;
  }

  @Override
  public JsonObject body() {
    return null;
  }

  @Override
  public String replyAddress() {
    return null;
  }

  @Override
  public boolean isSend() {
    return false;
  }

  @Override
  public void reply(Object message) {

  }

  @Override
  public <R> void reply(Object message, Handler<AsyncResult<Message<R>>> replyHandler) {

  }

  @Override
  public void reply(Object message, DeliveryOptions options) {

  }

  @Override
  public <R> void reply(Object message, DeliveryOptions options, Handler<AsyncResult<Message<R>>> replyHandler) {

  }

  @Override
  public void fail(int failureCode, String message) {

  }
}

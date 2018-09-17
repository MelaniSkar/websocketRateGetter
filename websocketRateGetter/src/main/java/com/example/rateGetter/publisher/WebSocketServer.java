package com.example.rateGetter.publisher;

import com.example.rateGetter.eventBus.Addresses;
import io.vertx.core.AbstractVerticle;
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
    router.route("/getrate/*").handler(getRateHandler());
    router.route().handler(staticHandler());
    vertx.createHttpServer()
      .requestHandler(router::accept)
      .listen(8080);
  }

  private SockJSHandler getRateHandler() {
    BridgeOptions options = new BridgeOptions()
      .addOutboundPermitted(new PermittedOptions().setAddressRegex(Addresses.PUBLISH_TO_BROWSER));

    SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
    return sockJSHandler.bridge(options, msg-> {
      System.out.println("opened");
    });
  }

  private StaticHandler staticHandler() {
    return StaticHandler.create()
      .setCachingEnabled(false);
  }

}

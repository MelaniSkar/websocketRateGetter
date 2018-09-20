

function init() {
  registerHandler();
}

let eventBus;

function registerHandler() {
  eventBus = new EventBus('http://localhost:8080/eventbus');
  eventBus.onopen = function () {
    eventBus.registerHandler('publish_bitfinex_rate', function (error, message) {
      const bitfinexRate = message.body;
      document.getElementById('bitfinexRate').innerHTML = bitfinexRate;
    });

    eventBus.registerHandler('publish_bittrex_rate', function (error, message) {
          const bittrexRate = message.body;
          document.getElementById('bittrexRate').innerHTML = bittrexRate;
        });

  }
}

function increment() {
  eventBus.send('in')
}

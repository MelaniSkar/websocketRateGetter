

function init() {
  registerHandler();
}

let eventBus;

function registerHandler() {
  eventBus = new EventBus('http://localhost:8080/eventbus');
  eventBus.onopen = function () {
    eventBus.registerHandler('publish_bitfinex_rate', function (error, message) {
        const newRate = message.body;
        const currentRate = Number(document.getElementById('bitfinexRate').innerHTML);
        const del = newRate - currentRate;
        if(del == 0) {
            document.getElementById("bitfinexDel").style.color = "black";
        } else if (del < 0) {
            document.getElementById("bitfinexDel").style.color = "red";
        } else
            document.getElementById("bitfinexDel").style.color = "green";

        document.getElementById('bitfinexRate').innerHTML = newRate;
        document.getElementById('bitfinexDel').innerHTML = del;
    });

    eventBus.registerHandler('publish_bittrex_rate', function (error, message) {
        const newRate = message.body;
        const currentRate = Number(document.getElementById('bittrexRate').innerHTML);
        const del = newRate - currentRate;
        if(del == 0) {
            document.getElementById("bittrexDel").style.color = "black";
        } else if (del < 0) {
            document.getElementById("bittrexDel").style.color = "red";
        } else
            document.getElementById("bittrexDel").style.color = "green";

        document.getElementById('bittrexRate').innerHTML = newRate;
        document.getElementById('bittrexDel').innerHTML = del;
    });

  }
}

function increment() {
  eventBus.send('in')
}

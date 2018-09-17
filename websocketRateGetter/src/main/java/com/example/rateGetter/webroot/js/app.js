function init() {
  registerHandler();
}

let eventBus;

function registerHandler() {
  eventBus = new EventBus('http://localhost:8080/getrate');
  eventBus.onopen = function () {
    eventBus.registerHandler("publish_to_browser", function (error, message) {
      const rate = message.body;
      document.getElementById('current_rate').innerHTML = rate;
    });
  }
}

package com.example.websocketRateGetter.repositories;

import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.SharedData;

import java.util.Optional;

public class RateRepository {

    private SharedData data;

    public RateRepository(SharedData data) {
        this.data = data;
    }

    public Optional<Double> get() {
        LocalMap<String, String> rate = data.getLocalMap("key");
        return Optional.of(rate)
                .filter(map -> !map.isEmpty())
                .map(map -> Double.valueOf(map.get("rate")));
    }

    public void update(Double rate) {
        LocalMap<String, String> map = data.getLocalMap("key");
        map.put("rate", rate.toString());
    }
}

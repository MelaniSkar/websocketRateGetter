package com.example.websocketRateGetter.repositories;

import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.SharedData;

import java.util.Optional;

public class RateRepository {

    private SharedData data;

    public RateRepository(SharedData data) {
        this.data = data;
    }

    public Optional<Double> get(String classname) {
        LocalMap<String, String> repo = data.getLocalMap(classname);
        return Optional.of(repo)
                .filter(map -> !map.isEmpty())
                .map(map -> Double.valueOf(map.get("rate")));
    }

    public Optional<Double> getPrevRate(String classname) {
        LocalMap<String, String> repo = data.getLocalMap(classname);
        return Optional.of(repo)
                .filter(map -> !map.isEmpty())
                .map(map -> Double.valueOf(map.get("prevRate")));
    }

    public void update(String classname, Double rate) {
        Optional<Double> currentRate = get(classname);
        LocalMap<String, String> map = data.getLocalMap(classname);
        map.put("prevRate", currentRate.toString());
        map.put("rate", rate.toString());
    }
}

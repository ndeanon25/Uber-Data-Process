package com.example;

import java.util.HashMap;

public class LocationDemand {
    private HashMap<String, Integer> demandMap;

    public LocationDemand() {
        demandMap = new HashMap<>();
    }

    public void addDemand(String location, int demand) {
        demandMap.put(location, demandMap.getOrDefault(location, 0) + demand);
    }

    public int getDemand(String location) {
        return demandMap.getOrDefault(location, 0);
    }

    public void printDemands() {
        for (String location : demandMap.keySet()) {
            System.out.println("Location: " + location + ", Demand: " + demandMap.get(location));
        }
    }
}
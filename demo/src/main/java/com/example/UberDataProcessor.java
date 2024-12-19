package com.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Processes Uber data from CSV files.
 */
public class UberDataProcessor {

    /**
     * Inner class to represent a Trip.
     * Trip object represents a single trip record in the CSV file.
     */
    public static class Trip {
        private final String key;
        private final double fareAmount;
        private final String pickupDatetime;
        private final double pickupLongitude;
        private final double pickupLatitude;
        private final double dropoffLongitude;
        private final double dropoffLatitude;
        private final int passengerCount;

        /**
         * Constructs a Trip instance.
         *
         * @param key              the trip key
         * @param fareAmount       the fare amount
         * @param pickupDatetime   the pickup datetime
         * @param pickupLongitude  the pickup longitude
         * @param pickupLatitude   the pickup latitude
         * @param dropoffLongitude the dropoff longitude
         * @param dropoffLatitude  the dropoff latitude
         * @param passengerCount   the passenger count
         */
        public Trip(String key, double fareAmount, String pickupDatetime,
                    double pickupLongitude, double pickupLatitude,
                    double dropoffLongitude, double dropoffLatitude,
                    int passengerCount) {
            this.key = key;
            this.fareAmount = fareAmount;
            this.pickupDatetime = pickupDatetime;
            this.pickupLongitude = pickupLongitude;
            this.pickupLatitude = pickupLatitude;
            this.dropoffLongitude = dropoffLongitude;
            this.dropoffLatitude = dropoffLatitude;
            this.passengerCount = passengerCount;
        }

        public String getPickupDatetime() {
            return pickupDatetime;
        }

        public double getPickupLongitude() {
            return pickupLongitude;
        }

        public double getPickupLatitude() {
            return pickupLatitude;
        }

        @Override
        public String toString() {
            return "Trip{" +
                    "key='" + key + '\'' +
                    ", fareAmount=" + fareAmount +
                    ", pickupDatetime='" + pickupDatetime + '\'' +
                    ", pickupLongitude=" + pickupLongitude +
                    ", pickupLatitude=" + pickupLatitude +
                    ", dropoffLongitude=" + dropoffLongitude +
                    ", dropoffLatitude=" + dropoffLatitude +
                    ", passengerCount=" + passengerCount +
                    '}';
        }
    }

    private List<Trip> trips;
    private DemandBST demandBST;
    private LocationDemand locationDemand;

    //create an empty list of trips, a demand BST, and a location demand object
    public UberDataProcessor() {
        trips = new ArrayList<>();
        demandBST = new DemandBST();
        locationDemand = new LocationDemand();
    }

        /**
         * Parses a CSV file and returns a list of Trip objects.
         *
         * @param filePath the path to the CSV file
         * @return a list of Trip objects
         * @throws IOException if an I/O error occurs
         */
        public List<Trip> parseCSV(String filePath) throws IOException {
            List<Trip> parsedTrips = new ArrayList<>();
            try (CSVParser parser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withHeader())) {
                for (CSVRecord record : parser) {
                    Trip trip = new Trip(
                            record.get("key"),
                            Double.parseDouble(record.get("fare_amount")),
                            record.get("pickup_datetime"),
                            Double.parseDouble(record.get("pickup_longitude")),
                            Double.parseDouble(record.get("pickup_latitude")),
                            Double.parseDouble(record.get("dropoff_longitude")),
                            Double.parseDouble(record.get("dropoff_latitude")),
                            Integer.parseInt(record.get("passenger_count"))
                    );
                    parsedTrips.add(trip);
                }
            }
            return parsedTrips;
        }
    }
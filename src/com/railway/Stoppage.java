package com.railway;

import java.time.LocalTime;

public class Stoppage {
    Station station;
    LocalTime arrivalTime;
    LocalTime departureTime;

    public Stoppage(Station station, LocalTime arrivalTime, LocalTime departureTime) {
        this.station = station;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public Station getStation() {
        return station;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }
}

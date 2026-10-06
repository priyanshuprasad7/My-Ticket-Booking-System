package com.railway;

import java.util.ArrayList;

public class RailwaySystem {

    private ArrayList<Station> stations;
    private ArrayList<Train> trains;
    private ArrayList<Booking> bookings;
    private int nextBookingNumber = 10001;

    public RailwaySystem() {
        stations = new ArrayList<>();
        trains = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public void addTrain(Train train) {
        trains.add(train);
    }

    public ArrayList<Station> getStation() {
        return stations;
    }

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public Station findStationByName(String stationName) {

        for (Station station : stations) {
            if (station.getStationName().equalsIgnoreCase(stationName)) {
                return station;
            }
        }
        return null;
    }
    public ArrayList<Booking> getBookings() {
        return bookings;
    }
    public Station findStationByCode ( String stationCode) {

        for(Station station : stations) {
            if(station.getStationCode().equalsIgnoreCase(stationCode)){
                return station;
            }
        }
        return null;
    }

    // public  ArrayList<Train> searchTrain(Station source, Station destination) {
    //     ArrayList<Train> matchingTrains = new ArrayList<>();
    //     for (Train train : trains) {
    //         boolean sourceFound = false;
    //         boolean destinationFound = false;
    //         int sourceIndex = -1;
    //         int destinationIndex = -1;
    //         ArrayList<Station> ListOfStoppages = train.getStoppages();
    //         for (int i = 0; i < ListOfStoppages.size(); i++) {
    //             Station station = ListOfStoppages.get(i);

    //             if (source.getStationName().equalsIgnoreCase(station.getStationName())) {
    //                 sourceIndex = i;
    //                 sourceFound = true;
    //             }
    //             if (destination.getStationName().equalsIgnoreCase(station.getStationName())) {
    //                 destinationIndex = i;
    //                 destinationFound = true;
    //             }
    //         }
    //         if (sourceFound && destinationFound && sourceIndex < destinationIndex) {
    //             matchingTrains.add(train);
    //         }
    //     }
    //     return matchingTrains;
    // }
    public String getBookingId() {
        String bookingId = "S" + nextBookingNumber;
        nextBookingNumber++;
        return bookingId;

    }
    public void addBooking(Booking booking){
        bookings.add(booking);
    }
    public void updateNextBookingNumber(ArrayList<Booking> bookings) {

        for(Booking booking : bookings) {
            String id = booking.getBookingId();
            if(id.startsWith("S")) {
                int number = Integer.parseInt(id.substring(1));
                if(number >= nextBookingNumber) {
                    nextBookingNumber = number + 1;
                }
            }
        }
    }
    public void printAllTrains() {

        if (trains.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }

        System.out.println("\n\n==================== ALL TRAINS ====================\n");

        for (Train train : trains) {

            System.out.println("Train No.  : " + train.getTrainNumber());
            System.out.println("Train Name : " + train.getTrainName());
            System.out.println("Total Seats: " + train.getTotalSeats());
            System.out.println("Available  : " + train.getAvailableSeats());

            System.out.println("-----------------------------------------------");
            System.out.printf(
                "%-20s %-15s %-15s%n",
                "Station",
                "Arrival",
                "Departure"
            );
            System.out.println("-----------------------------------------------");

            for (Stoppage stoppage : train.getStoppages()) {

                System.out.printf(
                    "%-20s %-15s %-15s%n",
                    stoppage.getStation().getStationName(),
                    stoppage.getArrivalTime(),
                    stoppage.getDepartureTime()
                );
            }

            System.out.println("================================================\n");
        }
    }
}
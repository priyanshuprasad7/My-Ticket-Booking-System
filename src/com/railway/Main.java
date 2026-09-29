package com.railway;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Station ara = new Station("ARA", "Ara", "Bhojpur");
        Station patna = new Station("PNBE", "Patna", "Patna");
        Station hajipur = new Station("HJP", "Hajipur", "Hajipur");
        Station muzaffarpur = new Station("MFP", "Muzaffarpur", "Muzaffarpur");
        Station darbhanga = new Station("DBG", "Darbhanga", "Darbhanga");
        Station madhubani = new Station("MBI", "Madhubani", "Madhubani");

        RailwaySystem railwaySystem = new RailwaySystem();
        railwaySystem.addStation(madhubani);
        railwaySystem.addStation(ara);
        railwaySystem.addStation(patna);
        railwaySystem.addStation(hajipur);
        railwaySystem.addStation(muzaffarpur);
        railwaySystem.addStation(darbhanga);

        ArrayList<Station> stoppagesOfBiharExpreStations = new ArrayList<>();
        stoppagesOfBiharExpreStations.add(ara);
        stoppagesOfBiharExpreStations.add(muzaffarpur);
        stoppagesOfBiharExpreStations.add(hajipur);
        stoppagesOfBiharExpreStations.add(darbhanga);
        stoppagesOfBiharExpreStations.add(madhubani);

        Train biharExpress = new Train(1001, "Bihar Express", stoppagesOfBiharExpreStations, 400);
        railwaySystem.addTrain(biharExpress);
        bookTicket(railwaySystem);
        

    }

    private static void bookTicket(RailwaySystem railwaySystem) {

        System.out.println("Good morning sir");
        System.out.print("Enter Your source : ");
        String src = sc.nextLine();
        System.out.print("Enter Your destination : ");
        String dest = sc.nextLine();
        Station source = railwaySystem.findStationByName(src);
        Station destination = railwaySystem.findStationByName(dest);
        if (source == null || destination == null) {
            System.out.println("Invalid source or destination station.");
        } else {
            ArrayList<Train> availableTrains = railwaySystem.searchTrain(source, destination);

            for (Train train : availableTrains) {
                System.out.println(" " + train.getTrainNumber() + " | " + train.getTrainName() +
                        " | Available seats :  " + train.getAvailableSeats());
            }

            System.out.println("Enter the train Number to book: ");
            int trainNumber = sc.nextInt();
            sc.nextLine();

            Train selectedTrain = null;
            for (Train train : availableTrains) {
                if (train.getTrainNumber() == trainNumber) {
                    selectedTrain = train;
                    break;
                }
            }
            if (selectedTrain == null) {
                System.out.println("Invalid Train Selection");
            } else {
                System.out.println("Your train Selection is " + selectedTrain.getTrainNumber()
                        + " " + selectedTrain.getTrainName());
            }

            if (selectedTrain.getAvailableSeats() <= 0) {
                System.out.println("Sorry , no seats left");
            } else {
                System.out.print("Enter passenger name: ");
                String name = sc.nextLine();

                System.out.print("Enter passenger age: ");
                int age = sc.nextInt();
                
                if(age<0){
                    System.out.println("Invalid age");
                    return ;
                }

                sc.nextLine();

                System.out.print("Enter passenger gender: ");
                String gender = sc.nextLine();

                Passenger passenger = new Passenger(name, age, gender);

                int seatNumber = selectedTrain.assignSeatNumber();
                if(seatNumber == -1){
                    System.out.println("Something went wrong");
                    return ;
                }
                String bookingId = railwaySystem.getBookingId();      
                String bookingStatus = "Confirmed";
                
                Booking booking = new Booking(bookingId, passenger, selectedTrain, source, destination, seatNumber, bookingStatus);

                railwaySystem.addBooking(booking);
                printTicket(booking);

            }

        }
    }

    private static void printStaionInformation(RailwaySystem railwaySystem) {

        for (Station station : railwaySystem.getStation()) {
            System.out.println(
                    station.getStationCode() + " | " +
                            station.getStationName() + " | " +
                            station.getDistrict() + " | ");
        }
    }

    private static void printTrainInformation(RailwaySystem railwaySystem) {
        for (Train train : railwaySystem.getTrains()) {
            System.out.println("Train No. : " + train.getTrainNumber()
                    + " " + train.getTrainName());
            printTrainStoppages(train.getStoppages());
            System.out.println(
                    "Total seats : " + train.getTotalSeats() + " ||  Available seats : " + train.getAvailableSeats());
        }
    }

    private static void printTrainStoppages(ArrayList<Station> stoppages) {
        System.out.println("Stoppages are");
        for (Station station : stoppages) {
            System.out.println(station.getStationCode() + " | " + station.getStationName());
        }
    }
    private static void printTicket(Booking booking) {

    System.out.println();
    System.out.println("============================================================");
    System.out.println("                  BIHAR RAILWAY");
    System.out.println("                    E-TICKET");
    System.out.println("============================================================");

    System.out.println();
    System.out.println("  Booking ID     : " + booking.getBookingId());
    System.out.println("  Status         : " + booking.getBookingStatus());

    System.out.println("------------------------------------------------------------");

    System.out.println("  Passenger Details");
    System.out.println("  Name           : " + booking.getPassenger().getName());
    System.out.println("  Age            : " + booking.getPassenger().getAge());
    System.out.println("  Gender         : " + booking.getPassenger().getGender());

    System.out.println("------------------------------------------------------------");

    System.out.println("  Journey Details");
    System.out.println("  Train No.      : " + booking.getTrain().getTrainNumber());
    System.out.println("  Train Name     : " + booking.getTrain().getTrainName());
    System.out.println("  From           : " + booking.getSource().getStationName());
    System.out.println("  To             : " + booking.getDestination().getStationName());
    System.out.println("  Seat No.       : " + booking.getSeatNumber());

    System.out.println("------------------------------------------------------------");
    System.out.println("  Thank you for booking with Bihar Railway!");
    System.out.println("  Please keep your Booking ID for future reference.");
    System.out.println("============================================================");
}

}
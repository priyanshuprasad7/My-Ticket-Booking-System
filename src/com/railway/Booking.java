package com.railway;

import java.io.Serializable;

public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;
    private Passenger passenger;
    private String bookingId;
    private Train train;
    private Station source;
    private Station destination;
    private int seatNumber;
    private String bookingStatus;

    public Booking(String bookingId,
               Passenger passenger,
               Train train,
               Station source,
               Station destination,
               int seatNumber,
               String bookingStatus) {

        this.bookingId = bookingId;
        this.passenger = passenger;
        this.train = train;
        this.source = source;
        this.destination = destination;
        this.seatNumber = seatNumber;
        this.bookingStatus = bookingStatus;
    }
    
    public String getBookingId() {
        return bookingId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Train getTrain() {
        return train;
    }

    public Station getSource() {
        return source;
    }

    public Station getDestination() {
        return destination;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }
}

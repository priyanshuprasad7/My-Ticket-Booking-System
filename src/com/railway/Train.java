package com.railway;

import  java.util.ArrayList;
public class Train{
    private int trainNumber;
    private String trainName;
    private ArrayList<Station> stoppages;
    private int totalSeats;
    private int availableSeats;
    private int nextSeatNumber = 1;

    public Train(int trainNumber , String trainName , ArrayList<Station>stoppages, int totalSeats){
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.stoppages = stoppages;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }
     public int getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public ArrayList<Station> getStoppages() {
        return stoppages;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
    // public boolean bookSeat (){
    //     if(availableSeats > 0){
    //         availableSeats --;
    //         return true;
    //     }
    //     return false;
    // }
    public int assignSeatNumber(){
        if(availableSeats > 0){
            int seatNumber = nextSeatNumber;
            availableSeats --;
            nextSeatNumber++;
            return  seatNumber;
        }
        return -1;
    }
}
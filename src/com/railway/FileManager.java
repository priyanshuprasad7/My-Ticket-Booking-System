package com.railway;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    
    public static void loadStations(RailwaySystem railwaySystem) {
         try {
            File obj = new File("../data/Stations.txt");
            Scanner file = new Scanner(obj);
            while(file.hasNextLine()){
                String line = file.nextLine();
                String data[] = line.split(",");
                String code = data[0];
                String name = data[1];
                String dist = data[2];
                Station station = new Station(code, name, dist);
                railwaySystem.addStation(station);           
            }
            file.close();
        } catch(IOException e){
            System.out.println("File not found");
        }
    }
    public static void loadTrains(RailwaySystem railwaySystem) {
         try {
            File obj= new File("../data/Trains.txt");
            Scanner file = new Scanner(obj);
            while(file.hasNextLine()) {
                String line = file.nextLine();
                String data[]= line.split("\\|");

                int trainNumber= Integer.parseInt(data[0]);
                String trainName = data[1];
                int totalSeats = Integer.parseInt(data[2]);
                String stoppageNames[] = data[3].split(",");
                ArrayList<Station> stoppages = new ArrayList<>();

                for(String stationName : stoppageNames){
                    Station station = railwaySystem.findStationByName(stationName);
                    if(station != null){
                        stoppages.add(station);
                    }
                }
                // This was for testing purpose that the stoppages are adding or not 
                // for(Station station : stoppages) {
                //     System.out.println(trainName + "  " + station.getStationName());
                // }
                Train train = new Train(trainNumber, trainName, stoppages, totalSeats);
                railwaySystem.addTrain(train);

            }
        } catch (IOException e){
            System.out.println("File not found");
        }
    }
    public static void saveBooking(Booking booking) {      
        try {;
            File file = new File("../data/booking.dat");
            FileOutputStream fos = new FileOutputStream(file, true);
            ObjectOutputStream oos;
            if(file.length() == 0) {
                oos = new ObjectOutputStream(fos);
            }else {
                oos = new AppendingObjectOutputStream(fos);
            }
            oos.writeObject(booking);
            oos.close();
        } catch (IOException e){
            System.out.println("File not found");
            e.printStackTrace();
        }

    }
    public static ArrayList<Booking> loadBooking () {
        ArrayList<Booking> bookings = new ArrayList<>();
            File file = new File("../data/booking.dat");

        if (!file.exists() || file.length() == 0) {
            return bookings;
        }
        try {
            FileInputStream fis = new FileInputStream( "../data/booking.dat");
            ObjectInputStream ois  = new ObjectInputStream(fis);

            while(true) {
                try {
                    Booking booking = (Booking) ois.readObject();
                    bookings.add(booking);
                } catch (EOFException e){
                    break;
                }
            }
            return bookings;
        } catch (IOException | ClassNotFoundException e ) {
            System.out.println("Error loading file");
            e.printStackTrace();
            return null;
        }
    }
}

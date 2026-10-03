package com.railway;

import java.io.Serializable;

public class Station implements Serializable {
    private String stationCode;
    private String stationName;
    private String district;
    
    public Station(String stationCode , String stationName , String district){
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.district = district;
    }
    public String getStationName (){
       return stationName;
    }
    public String getStationCode(){
        return stationCode;
    }
    public String getDistrict(){
        return district;
    }
}

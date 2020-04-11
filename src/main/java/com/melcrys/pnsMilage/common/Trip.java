package com.melcrys.pnsMilage.common;

import java.util.Date;

public class Trip {
    private final Date travelDate;
    private final Location travelLocation;
    private final String travelPurpose;

    public Trip(Date travelDate, Location travelLocation, String travelPurpose){
        this.travelDate=travelDate;
        this.travelLocation=travelLocation;
        this.travelPurpose=travelPurpose;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public Location getTravelLocation() {
        return travelLocation;
    }

    public String getTravelPurpose() {
        return travelPurpose;
    }
}

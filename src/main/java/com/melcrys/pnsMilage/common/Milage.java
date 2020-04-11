package com.melcrys.pnsMilage.common;

import java.util.List;

public class Milage {
    private final String fName;
    private final String lName;
    private final String employeeNumber;
    private final List<Trip> trips;


    public Milage(String fName, String lName, String employeeNumber, List<Trip> trips){
        this.fName=fName;
        this.lName=lName;
        this.employeeNumber=employeeNumber;
        this.trips=trips;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public List<Trip> getTrips() {
        return trips;
    }
}

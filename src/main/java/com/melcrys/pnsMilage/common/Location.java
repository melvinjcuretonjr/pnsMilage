package com.melcrys.pnsMilage.common;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Location {
    private final BigDecimal distance;
    private final String dept;
    private final String name;

    public static final Location CLEMSON = new Location(new BigDecimal("36"),"AH Clemson Site","000");
    public static final Location HARTWELL = new Location(new BigDecimal("50"),"AH Hartwell","519");
    public static final Location HONEA_PATH = new Location(new BigDecimal("40"),"AH Honea Path","557");
    public static final Location IVA = new Location(new BigDecimal("33"),"AH Iva","521");
    public static final Location LAKESIDE = new Location(new BigDecimal("16"),"AH Lakeside","598");
    public static final Location CENTERVILLE = new Location(new BigDecimal("10"),"AH Centerville","589");
    public static final Location PENDLETON = new Location(new BigDecimal("27"),"AH Pendelton","518");
    public static final Location PALMETTO = new Location(new BigDecimal("39"),"AHPalmetto","531");
    public static final Location WILLIAMSTON = new Location(new BigDecimal("30"),"AH Pendelton","515");
    public static final Location WREN = new Location(new BigDecimal("29"),"AH Wren","533");

    private Location(BigDecimal distance, String name, String dept){
        this.distance=distance;
        this.name=name;
        this.dept=dept;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public String getDept() {
        return dept;
    }

    public String getName() {
        return name;
    }

    public static List<Field> getLocations(){
        return Arrays.asList(Location.class.getFields());
    }
}

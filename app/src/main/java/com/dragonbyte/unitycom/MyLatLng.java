package com.dragonbyte.unitycom;

/**
 * Created by Antonia on 4/20/2017.
 */

public class MyLatLng {

    private double latitude;
    private double longitude;

    public MyLatLng(double lat, double longi) {
        this.latitude = lat;
        this.longitude = longi;
    }

    public MyLatLng() {

    }

    public double getLatitude() { return this.latitude; }

    public double getLongitude() { return this.longitude; }

    public void setLatitude(double lat) { this.latitude = lat; }
    public void setLongitude(double longi) { this.longitude = longi; }
}

package com.dragonbyte.unitycom;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonia on 4/17/2017.
 */

public class User {
    private  String email;
    private String password;
    private String firstName;
    private String secondName;
    private String telephone;
    private String address;
    private double latitude;
    private double longitude;
    private String products;


    public User() {
    }

    public User(String firstName, String secondName, String email, String password, String telephone, String address) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.address = address;
        this.products = " ";


    }

    public String getProducts() { return this.products; }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) { this.address = address; }

    public void setListOfProducts(String products) {
        this.products = products;
    }

    public void setLatitude(double lat) { this.latitude = lat; }

    public double getLatitude() { return this.longitude; }

    public void setLongitude(double lng) { this.longitude = lng; }

    public double getLongitude() { return this.longitude; }
}


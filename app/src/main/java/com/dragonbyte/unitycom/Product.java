package com.dragonbyte.unitycom;

/**
 * Created by Antonia on 4/17/2017.
 */

public class Product {
    String email;
    String address;
    String contact;
    String name;

    public Product () {

    }

    public Product(String email, String name, String address, String contact) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.contact = contact;

    }

    public String getemail() { return this.email; }
    public void setemail(String email) { this.email = email; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return this.address; }
    public void setAddres(String address) {
        this.address = address;
    }

    public String getContact() { return this.contact;}
    public void setContact(String contact) { this.contact = contact; }


}

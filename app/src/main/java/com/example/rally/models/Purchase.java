package com.example.rally.models;

import java.util.Date;

public class Purchase {
    private String item;
    private double price;
    private Date date;
    private String profile_ID;
    private String PURCHASE_ID;

    //Constructors
    public Purchase(String item, double price, Date date, String profile_ID) {
        this.item = item;
        this.price = price;
        this.date = date;
        this.profile_ID = profile_ID;
    }

    //"set" functions
    public void setItem(String item) {
        this.item = item;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setProfile_ID(String profile_ID) {
        this.profile_ID = profile_ID;
    }

    public String getPURCHASE_ID() {
        return PURCHASE_ID;
    }

    //"get" functions
    public String getItem() {
        return item;
    }

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public String getProfile_ID() {
        return profile_ID;
    }

    public void setPURCHASE_ID(String PURCHASE_ID) {
        this.PURCHASE_ID = PURCHASE_ID;
    }
}

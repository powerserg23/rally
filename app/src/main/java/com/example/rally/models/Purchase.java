package com.example.rally.models;

import java.util.Calendar;
import java.util.Date;

public class Purchase {
    private String item;
    private double price;
    private Calendar date;
    //private String tag;
    private String PURCHASE_ID;

    //Constructors
    public Purchase(String item, double price, Calendar date) {
        this.item = item;
        this.price = price;
        this.date = date;
        //this.tag = tag;
    }

    //"set" functions
    public void setItem(String item) {
        this.item = item;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(Calendar date) {
        this.date = date;
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

    public Calendar getDate() {
        return date;
    }


    public void setPURCHASE_ID(String PURCHASE_ID) {
        this.PURCHASE_ID = PURCHASE_ID;
    }
}

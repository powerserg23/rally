package com.example.rally.models;

//import android.widget.ImageView;

import java.util.List;

public class Profile {
    private String name;
    private String email;
    private String UNIQUE_ID;
    public String imageurl;
    //private ImageView prof_image;


    public Profile(String name, String email)
    {
        this.name=name;
        this.email=email;
    }

    public Profile(String name, String email, String imageurl)
    {
        this.name=name;
        this.email=email;
        this.imageurl=imageurl;
    }

    //this constructor is only used for testing before we have implemented an image for the profile
    public Profile(String name)
    {
        this.name=name;

    }

    //"set" functions
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setUNIQUE_ID(String UNIQUE_ID) {
        this.UNIQUE_ID = UNIQUE_ID;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    /*
    public void setProf_image(ImageView prof_image) {
        this.prof_image = prof_image;
    }
    */

    //"get" functions
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUNIQUE_ID() {
        return UNIQUE_ID;
    }

    public String getImageurl() {
        return imageurl;
    }

    /*
    public ImageView getProf_image() {
        return prof_image;
    }
    */

}
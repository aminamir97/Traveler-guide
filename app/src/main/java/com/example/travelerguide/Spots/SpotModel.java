package com.example.travelerguide.Spots;

import java.io.Serializable;
import java.util.Map;

public class SpotModel implements Serializable {
   public String name;
    public String city;
    public String image;
    public String rate;
    public String comments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }



    public SpotModel(String name, String city, String image, String rate, String comments) {
        this.name = name;
        this.city = city;
        this.image = image;
        this.rate = rate;
        this.comments = comments;
    }



}

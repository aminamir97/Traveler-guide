package com.example.travelerguide.coupons;

import java.io.Serializable;

public class CouponModel implements Serializable {
    private  String name,city,code,image;

    public CouponModel(String name, String city, String code, String image) {
        this.name = name;
        this.city = city;
        this.code = code;
        this.image = image;
    }

    public  CouponModel()
    {

    }
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

package com.gmail.tarasov1998.wfu2;

import java.io.Serializable;

public class Location implements Serializable {
    private static String city;
    private static String country;

    public static String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public static String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }



}

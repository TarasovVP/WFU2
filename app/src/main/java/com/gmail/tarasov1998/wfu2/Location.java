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

    static String getCountry() {
        return country;
    }
    void setCountry(String country) {
        this.country = country;
    }



}

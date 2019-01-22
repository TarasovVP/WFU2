package com.gmail.tarasov1998.wfu2;

public class Location {
    private String userData, userCity, userCountry;

    String getuserData() {
        return userData;
    }
    void setuserData(String userData) {
        this.userData = userData;
    }

    String getUserCity() {
        return userCity;
    }
    void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    String getUserCountry() {
        return userCountry;
    }
    void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }
}

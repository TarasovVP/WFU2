package com.gmail.tarasov1998.wfu2;

import java.util.ArrayList;

public class Location {
    private ArrayList<String> listCities = new ArrayList<>();
    private ArrayList<String> listCountries = new ArrayList<>();
    private ArrayList<Integer> listId = new ArrayList<>();
    private int count;
    private String cityRU;



    public int getId(int index) {
        return listId.get(index);
    }

    public void setId(int id) {
        listId.add(id);
    }

    int getCount() {
        return count;
    }

    void setCount(int count) {
        this.count = count;
    }

    String getUserCity(int index) {
        return listCities.get(index);
    }

    void setUserCity(String userCity) {
        listCities.add(userCity);
    }

    String getUserCountry(int index) {
        return listCountries.get(index);
    }

    void setUserCountry(String userCountry) {
        listCountries.add(userCountry);
    }

    String getCityRU() {
        return cityRU;
    }

    void setCityRU(String cityRU) {
        this.cityRU = cityRU;
    }

}

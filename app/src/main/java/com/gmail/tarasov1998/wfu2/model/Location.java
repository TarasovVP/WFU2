package com.gmail.tarasov1998.wfu2.model;

import java.util.ArrayList;

public class Location {
    private ArrayList<String> listCities = new ArrayList<>();
    private ArrayList<String> listCountries = new ArrayList<>();
    private ArrayList<Integer> listId = new ArrayList<>();
    private int count;



    public int getId(int index) {
        return listId.get(index);
    }

    public void setId(int id) {
        listId.add(id);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUserCity(int index) {
        return listCities.get(index);
    }

    public void setUserCity(String userCity) {
        listCities.add(userCity);
    }

    public String getUserCountry(int index) {
        return listCountries.get(index);
    }

    public void setUserCountry(String userCountry) {
        listCountries.add(userCountry);
    }


}

package com.gmail.tarasov1998.wfu2;

import java.util.ArrayList;

public class Weather {

    private ArrayList<Float> listTemp = new ArrayList<>();
    private ArrayList<Integer> listId = new ArrayList<>();
    private ArrayList<String> listIcon = new ArrayList<>();



    public int getIdWeather(int index) {

        return listId.get(index);
    }

    public void setIdWeather(int idWeather) {
        listId.add(idWeather);
    }

    public float getTemp(int index) {
        return listTemp.get(index);
    }

    public void setTemp(float temp) {
        listTemp.add(temp);
    }

    public String getIcon(int index) {
        return listIcon.get(index);
    }

    public void setIcon(String icon) {
        listIcon.add(icon);
    }



}

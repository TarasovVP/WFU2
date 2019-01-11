package com.gmail.tarasov1998.wfu2;

import java.util.ArrayList;
import java.util.Date;

public class Weather {

    private ArrayList<Float> listTemp = new ArrayList<>();
    private ArrayList<Integer> listId = new ArrayList<>();
    private ArrayList<String> listIcon = new ArrayList<>();
    private ArrayList<String> listTime = new ArrayList<>();



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

    public String getTime(int index) {

        return listTime.get(index);
    }

    public void setTime(String date) {
        listTime.add(date);


    }



}

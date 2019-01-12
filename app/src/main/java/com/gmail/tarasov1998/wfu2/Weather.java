package com.gmail.tarasov1998.wfu2;

import org.joda.time.LocalTime;
import java.util.ArrayList;

public class Weather {
    private ArrayList<String> listTime = new ArrayList<>();
    private ArrayList<Float> listTemp = new ArrayList<>();
    private ArrayList<Integer> listId = new ArrayList<>();
    private ArrayList<String> listIcon = new ArrayList<>();


    public int getIdWeather(int index) {

        return listId.get(index);
    }

    public void setIdWeather(int idWeather) {
        listId.add(idWeather);
    }

    float getTemp(int index) {
        return listTemp.get(index);
    }

    void setTemp(float temp) {
        listTemp.add(temp);
    }

    String getIcon(int index) {
        return listIcon.get(index);
    }

    void setIcon(String icon) {
        listIcon.add(icon);
    }

    LocalTime getTime(int index) {
        return LocalTime.parse(listTime.get(index).substring(11, 16));
    }

    public void setTime(String date) {
        listTime.add(date);
    }

    public int choiseIconWeather(String getIcon) {

        int resIcon = 0;
        switch (getIcon) {
            case "01d":
                resIcon = R.drawable.clear_sky_day;
                break;
            case "01n":
                resIcon = R.drawable.clear_sky_night;
                break;
            case "02d":
                resIcon = R.drawable.few_clouds_day;
                break;
            case "02n":
                resIcon = R.drawable.few_clouds_night;
                break;
            case "03d":
            case "03n":
                resIcon = R.drawable.scattered_clouds_night;
                break;
            case "04d":
                resIcon = R.drawable.broken_clouds_day;
                break;
            case "04n":
                resIcon = R.drawable.broken_clouds_night;
                break;
            case "09d":
                resIcon = R.drawable.clear_sky_day;
                break;
            case "09n":
                resIcon = R.drawable.shower_rain_night;
                break;
            case "10d":
                resIcon = R.drawable.rain_day;
                break;
            case "10n":
                resIcon = R.drawable.rain_night;
                break;
            case "11d":
                resIcon = R.drawable.thunderstorm_day;
                break;
            case "11n":
                resIcon = R.drawable.thunderstorm_night;
                break;
            case "13d":
                resIcon = R.drawable.snow_day;
                break;
            case "13n":
                resIcon = R.drawable.snow_night;
                break;
            case "50d":
            case "50n":
                resIcon = R.drawable.mist_day;
                break;
            default:
                break;

        }
        return resIcon;

    }


}



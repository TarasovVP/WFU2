package com.gmail.tarasov1998.wfu2;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

class GetWeathear {

    static Weather getWeather(String data) throws JSONException {
        Weather weather = new Weather();
        JSONObject jObj = new JSONObject(data);

            JSONObject locObj = jObj.getJSONObject("city");
            weather.setCity(getString("name", locObj));
            weather.setCountry(getString("country", locObj));


        JSONArray jArr = jObj.getJSONArray("list");
        for (int i = 0; i <= 5; i++) {
            JSONObject mWeather = jArr.getJSONObject(i);

            JSONArray jArrWeath = mWeather.getJSONArray("weather");
            JSONObject idWeather = jArrWeath.getJSONObject(0);
            weather.setIcon(getString("icon", idWeather));

            JSONObject mainObj = mWeather.getJSONObject("main");
            weather.setTemp((float) mainObj.getDouble("temp"));

            weather.setTime(getString("dt_txt", mWeather));
        }


        return weather;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }


}

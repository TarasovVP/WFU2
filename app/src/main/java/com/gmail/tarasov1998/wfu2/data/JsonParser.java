package com.gmail.tarasov1998.wfu2.data;


import com.gmail.tarasov1998.wfu2.model.Location;
import com.gmail.tarasov1998.wfu2.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    public static Weather getWeather(String data) throws JSONException {
        Weather weather = new Weather();
        JSONObject jObj = new JSONObject(data);

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

    public static Location getLocation(String data) throws Exception {
        Location location = new Location();
        JSONObject jObjList = new JSONObject(data.substring(2, data.length() - 1));

        location.setCount(jObjList.getInt("count"));

        if(location.getCount() > 0){
            JSONArray jArrLoc = jObjList.getJSONArray("list");

            for (int i = 0; i < location.getCount(); i++) {
                JSONObject citiesList = jArrLoc.getJSONObject(i);

                location.setUserCity(getString("name", citiesList));
                int id = citiesList.getInt("id");
                location.setId(id);

                JSONObject country = citiesList.getJSONObject("sys");

                location.setUserCountry(getString("country", country));

            }


        }

        return location;
    }


    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }


}

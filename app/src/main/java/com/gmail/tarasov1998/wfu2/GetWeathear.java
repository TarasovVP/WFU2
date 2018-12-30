package com.gmail.tarasov1998.wfu2;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class GetWeathear {

    public static Weather getWeather(String data) throws JSONException {
        Weather weather = new Weather();

        JSONObject jObj = new JSONObject(data);

        Location loc = new Location();
        loc.setCity(getString("name", jObj));
        weather.location = loc;

        JSONArray jArr = jObj.getJSONArray("weather");
        JSONObject mainWeather = jArr.getJSONObject(0);
        weather.mainWeather.setMainWeather(getString("main", mainWeather));


        JSONObject mainObj = getObject("main", jObj);
        weather.temperature.setTemp(getFloat("temp", mainObj));


        return weather;
    }

    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }
}

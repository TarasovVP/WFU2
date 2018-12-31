package com.gmail.tarasov1998.wfu2;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class GetWeathear {

    public static Weather getWeather(String data) throws JSONException {
        Weather weather = new Weather();

        JSONObject jObj = new JSONObject(data);

        Location loc = new Location();
        JSONObject locObj = jObj.getJSONObject("city");
        loc.setCity(getString("name", locObj));
        //weather.location = loc;

        JSONArray jArr = jObj.getJSONArray("list");
        JSONObject mWeather = jArr.getJSONObject(0);
        JSONArray jArr2 = mWeather.getJSONArray("weather");
        JSONObject idWeather = jArr2.getJSONObject(0);
        weather.setIdWeather(getInt("id", idWeather));

        JSONObject mainObj = getObject("main", mWeather);
        weather.setTemp(getFloat("temp", mainObj));


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
    private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return (int) jObj.getInt(tagName);
    }
}

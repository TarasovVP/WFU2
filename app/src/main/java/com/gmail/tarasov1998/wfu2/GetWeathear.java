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

        JSONArray jArr = jObj.getJSONArray("list");
        JSONObject mWeather = jArr.getJSONObject(0);
        JSONArray jArrWeath = mWeather.getJSONArray("weather");
        JSONObject idWeather = jArrWeath.getJSONObject(0);
        weather.setIdWeather(getInt("id", idWeather));

        JSONObject mWeather1 = jArr.getJSONObject(1);
        JSONArray jArrWeath1 = mWeather1.getJSONArray("weather");
        JSONObject idWeather1 = jArrWeath1.getJSONObject(0);
        weather.setIdWeather(getInt("id", idWeather1));

        JSONObject mWeather2 = jArr.getJSONObject(2);
        JSONArray jArrWeath2 = mWeather2.getJSONArray("weather");
        JSONObject idWeather2 = jArrWeath2.getJSONObject(0);
        weather.setIdWeather(getInt("id", idWeather2));

        JSONObject mWeather3 = jArr.getJSONObject(3);
        JSONArray jArrWeath3 = mWeather3.getJSONArray("weather");
        JSONObject idWeather3 = jArrWeath3.getJSONObject(0);
        weather.setIdWeather(getInt("id", idWeather3));

        JSONObject mWeather4 = jArr.getJSONObject(4);
        JSONArray jArrWeath4 = mWeather4.getJSONArray("weather");
        JSONObject idWeather4 = jArrWeath4.getJSONObject(0);
        weather.setIdWeather(getInt("id", idWeather4));

        JSONObject mWeather5 = jArr.getJSONObject(5);
        JSONArray jArrWeath5 = mWeather5.getJSONArray("weather");
        JSONObject idWeather5 = jArrWeath5.getJSONObject(0);
        weather.setIdWeather(getInt("id", idWeather5));



        JSONObject mainObj = getObject("main", mWeather);
        weather.setTemp(getFloat("temp", mainObj));

        JSONObject mainObj1 = getObject("main", mWeather1);
        weather.setTemp(getFloat("temp", mainObj1));

        JSONObject mainObj2 = getObject("main", mWeather2);
        weather.setTemp(getFloat("temp", mainObj2));

        JSONObject mainObj3 = getObject("main", mWeather3);
        weather.setTemp(getFloat("temp", mainObj3));

        JSONObject mainObj4 = getObject("main", mWeather4);
        weather.setTemp(getFloat("temp", mainObj4));

        JSONObject mainObj5 = getObject("main", mWeather5);
        weather.setTemp(getFloat("temp", mainObj5));


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

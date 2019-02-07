package com.gmail.tarasov1998.wfu2;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class GetJson {

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

    static Location getLocation(String data) throws Exception {
        Location location = new Location();
        JSONObject jObjList = new JSONObject(data.substring(2, data.length() - 1));

        location.setCount(jObjList.getInt("count"));

        JSONArray jArrLoc = jObjList.getJSONArray("list");

        for (int i = 0; i < location.getCount(); i++) {
            JSONObject citiesList = jArrLoc.getJSONObject(i);

            location.setUserCity(getString("name", citiesList));

            JSONObject country = citiesList.getJSONObject("sys");
            location.setUserCountry(getString("country", country));

        }

        return location;
    }

    static Location getTranslate(String data) throws JSONException
    {
        Location location = new Location();

        JSONArray jObj = new JSONArray(data);
        JSONObject jsob = jObj.getJSONObject(0);
        JSONArray jsob2 = jsob.getJSONArray("translations");
        JSONObject josbj = jsob2.getJSONObject(0);

        location.setCityRU(getString("text", josbj));


        return location;
    }


    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }


}

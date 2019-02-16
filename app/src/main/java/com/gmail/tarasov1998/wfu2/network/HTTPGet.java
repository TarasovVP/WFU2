package com.gmail.tarasov1998.wfu2.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPGet {
    private static final String KEY = "&appid=b6907d289e10d714a6e88b30761fae22";
    private static final String FIND_URL = "https://openweathermap.org//data/2.5/find?callback=?&q=";
    private static final String PARAM = "&type=like&sort=population&cnt=30";
    private static final String BASE_URL = "http://openweathermap.org/data/2.5/forecast?q=";

    private String httpget(String data) {
        HttpURLConnection con = null;
        StringBuffer buffer = null;
        try {
            con = (HttpURLConnection) (new URL( data )).openConnection();
            con.connect();

            try (InputStream is = con.getInputStream()) {
                buffer = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = br.readLine()) != null)
                    buffer.append(line).append("\r\n");


            } catch (Throwable t) {
                t.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        return buffer != null ? buffer.toString() : null;

    }

    public String getLocationData(String location){
        return httpget(FIND_URL + location + PARAM + KEY);
    }

    public String getWeatherData(Integer request){
        return httpget(BASE_URL + request + KEY);
    }
}

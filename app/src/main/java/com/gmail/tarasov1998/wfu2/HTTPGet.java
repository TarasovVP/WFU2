package com.gmail.tarasov1998.wfu2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class HTTPGet {
    private String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private String FIND_URL = "https://openweathermap.org//data/2.5/find?callback=?&q=";

    String getLocationData(String location){
        return httpget(FIND_URL + location + "&type=like&sort=population&cnt=30");
    }

    String getWeatherData(String request){
        return httpget(BASE_URL + request + "&APPID=824dca49c6fce4716e4f85bf1e4e60e6");
    }

    private String httpget(String data) {
        HttpURLConnection con = null;
        StringBuffer buffer = null;
        try {
            con = (HttpURLConnection) (new URL( data )).openConnection();
            con.connect();

            try (InputStream is = con.getInputStream()) {
                buffer = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = null;
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
}

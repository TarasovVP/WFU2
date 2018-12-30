package com.gmail.tarasov1998.wfu2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView cityShow;
    private TextView temperature;
    private TextView dateTime;

    private ImageView mainWeather;


    String city = "Dnipro";
    String country = "UA";

    //Get local date and time
    DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy \n HH:mm");
    Calendar cal = Calendar.getInstance();
    String date = dateFormat.format(cal.getTime());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set date and time in TextView
        dateTime = (TextView) findViewById(R.id.date);
        dateTime.setText(date);


        cityShow = (TextView) findViewById(R.id.cityShow);
        temperature = (TextView) findViewById(R.id.temperature);
        mainWeather = (ImageView) findViewById(R.id.mainWeather);

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city + "," + country});

    }


    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ((new HTTPGet()).httpget(params[0]));

            try {
                weather = GetWeathear.getWeather(data);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

                cityShow.setText(Location.getCity());
                temperature.setText("" + Math.round((weather.temperature.getTemp() - 273.15)));
                mainWeather.setImageResource(choiseIconWeather(weather.idWeather.getIdWeather()));

        }
        public int choiseIconWeather(int getId) {

            int resID = 0;
            if (getId == 800) {
                resID = R.drawable.clearsky;
            } else if (getId == 801) {
                resID = R.drawable.fewclouds;
            }else if (getId == 802) {
                resID = R.drawable.scatteredclouds;
            }else if (getId == 803 || getId == 804) {
                resID = R.drawable.brokenclouds;
            }else if (getId%100 == 2 || getId == 520 || getId == 521 || getId == 522 || getId == 531) {
                resID = R.drawable.showerrain;
            }else if (getId == 500 || getId == 501 || getId == 502 || getId == 503|| getId == 504) {
                resID = R.drawable.rain;
            }else if (getId%100 == 2) {
                resID = R.drawable.thunderstorm;
            }else if (getId%100 == 6) {
                resID = R.drawable.snow;
            }else if (getId%100 == 7) {
                resID = R.drawable.mist;
            }else {
                resID = R.drawable.questionmark;
            }return resID;
    }


    }


}
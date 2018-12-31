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

    private TextView temp1;
    private TextView temp2;
    private TextView temp3;
    private TextView temp4;
    private TextView temp5;

    private ImageView weath1;
    private ImageView weath2;
    private ImageView weath3;
    private ImageView weath4;
    private ImageView weath5;

    private TextView date1;
    private TextView date2;
    private TextView date3;
    private TextView date4;
    private TextView date5;

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

        temp1 = (TextView) findViewById(R.id.temp1);
        temp2 = (TextView) findViewById(R.id.temp2);
        temp3 = (TextView) findViewById(R.id.temp3);
        temp4 = (TextView) findViewById(R.id.temp4);
        temp5 = (TextView) findViewById(R.id.temp5);

        weath1 = (ImageView) findViewById(R.id.weath1);
        weath2 = (ImageView) findViewById(R.id.weath2);
        weath3 = (ImageView) findViewById(R.id.weath3);
        weath4 = (ImageView) findViewById(R.id.weath4);
        weath5 = (ImageView) findViewById(R.id.weath5);

        date1 = (TextView) findViewById(R.id.date1);
        date2 = (TextView) findViewById(R.id.date2);
        date3 = (TextView) findViewById(R.id.date3);
        date4 = (TextView) findViewById(R.id.date4);
        date5 = (TextView) findViewById(R.id.date5);

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
                temperature.setText("" + Math.round((weather.getTemp() - 273.15)));
                mainWeather.setImageResource(choiseIconWeather(weather.getIdWeather()));

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
            }else if (getId/100 == 2 || getId == 520 || getId == 521 || getId == 522 || getId == 531) {
                resID = R.drawable.showerrain;
            }else if (getId == 500 || getId == 501 || getId == 502 || getId == 503|| getId == 504) {
                resID = R.drawable.rain;
            }else if (getId/100 == 2) {
                resID = R.drawable.thunderstorm;
            }else if (getId/100 == 6) {
                resID = R.drawable.snow;
            }else if (getId/100 == 7) {
                resID = R.drawable.mist;
            }else {
                resID = R.drawable.questionmark;
            }return resID;
    }


    }


}
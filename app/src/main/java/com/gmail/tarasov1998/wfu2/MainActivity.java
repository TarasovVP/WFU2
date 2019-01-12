package com.gmail.tarasov1998.wfu2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.LocalDateTime;


public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "TAG";

    String city;

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


    LocalDateTime date = LocalDateTime.now();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        city = intent.getStringExtra("city");

        //Set date and time in TextView
        dateTime = (TextView) findViewById(R.id.date);
        dateTime.setText(date.toString("dd MMMM yyyy \n HH:mm"));

        date1 = (TextView) findViewById(R.id.date1);
        date2 = (TextView) findViewById(R.id.date2);
        date3 = (TextView) findViewById(R.id.date3);
        date4 = (TextView) findViewById(R.id.date4);
        date5 = (TextView) findViewById(R.id.date5);


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


        JSONWeatherTask task = new JSONWeatherTask();

        task.execute(city);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Выбрать город");

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, ActivityStart.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ((new HTTPGet()).httpget(params[0]));

            try {
                if(data == null) {
                    return null;
                }else{
                    weather = GetWeathear.getWeather(data);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return weather;

        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            if(weather != null) {
                cityShow.setText(Location.getCity() + ", " + Location.getCountry());
                temperature.setText("" + Math.round((weather.getTemp(0) - 273.15))+"°");
                mainWeather.setImageResource(weather.choiseIconWeather(weather.getIcon(0)));

                date1.setText(weather.getTime(1).toString("HH:mm"));
                date2.setText(weather.getTime(2).toString("HH:mm"));
                date3.setText(weather.getTime(3).toString("HH:mm"));
                date4.setText(weather.getTime(4).toString("HH:mm"));
                date5.setText(weather.getTime(5).toString("HH:mm"));

                temp1.setText("" + Math.round((weather.getTemp(1) - 273.15))+"°");
                temp2.setText("" + Math.round((weather.getTemp(2) - 273.15))+"°");
                temp3.setText("" + Math.round((weather.getTemp(3) - 273.15))+"°");
                temp4.setText("" + Math.round((weather.getTemp(4) - 273.15))+"°");
                temp5.setText("" + Math.round((weather.getTemp(5) - 273.15))+"°");


                weath1.setImageResource(weather.choiseIconWeather(weather.getIcon(1)));
                weath2.setImageResource(weather.choiseIconWeather(weather.getIcon(2)));
                weath3.setImageResource(weather.choiseIconWeather(weather.getIcon(3)));
                weath4.setImageResource(weather.choiseIconWeather(weather.getIcon(4)));
                weath5.setImageResource(weather.choiseIconWeather(weather.getIcon(5)));


            }else{
                Intent intent = new Intent(getApplicationContext(), ActivityStart.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Ничего не найдено", Toast.LENGTH_LONG).show();

            }

        }


    }


}
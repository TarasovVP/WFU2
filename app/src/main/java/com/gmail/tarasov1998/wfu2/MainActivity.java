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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView cityShow;
    private TextView temperature;
    private TextView dateTime;
    private TextView txtWeather;

    private ImageView mainWeather;

    String city = "Dnipro";
    String country = "UA";

    DateTime nowDateTime = new DateTime();
    LocalDateTime nowDT = nowDateTime.withZone(DateTimeZone.UTC).toLocalDateTime();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button back = (Button) findViewById(R.id.back);
        back.setText("Возврат в главное меню");

        back.setOnClickListener(this);

        cityShow = (TextView) findViewById(R.id.cityShow);
        temperature = (TextView) findViewById(R.id.temperature);
        dateTime = (TextView) findViewById(R.id.date);
        mainWeather = (ImageView) findViewById(R.id.mainWeather);
        txtWeather = (TextView) findViewById(R.id.txtWeather);

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city+","+country});

    }

    @Override
    public void onClick(View v) {
                Intent intent = new Intent(this, ActivityStart.class);
                startActivityForResult(intent, 1);

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        String nameCity = data.getStringExtra("city");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 1, "Город");
        menu.add(0, 2, 2, "Дата");
        menu.add(0, 3, 3, "Температура");
        menu.add(0, 4, 4, "Время");

        return super.onCreateOptionsMenu(menu);
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
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
            dateTime.setText(nowDT.toString("dd MMMM yyyy \n HH:mm"));
            txtWeather.setText(weather.mainWeather.getMainWeather());
            mainWeather.setImageResource(choiseIconWeater(weather.mainWeather.getMainWeather()));

        }

    }

    public int choiseIconWeater(String getWeather){
        int resID = 0;
        switch (getWeather){
            case ("clear sky"):
                resID = R.drawable.clearsky;
                break;
            case ("few clouds"):
                resID = R.drawable.fewclouds;
                break;
            case ("scattered clouds "):
                resID = R.drawable.scatteredclouds ;
                break;
            case ("broken clouds"):
                resID = R.drawable.brokenclouds ;
                break;
            case ("shower rain "):
                resID = R.drawable.showerrain ;
                break;
            case ("rain"):
                resID = R.drawable.rain;
                break;
            case ("thunderstorm"):
                resID = R.drawable.thunderstorm;
                break;
            case ("snow"):
                resID = R.drawable.snow;
                break;
            case ("mist"):
                resID = R.drawable.mist;
                break;
            default:
                resID = R.drawable.questionmark;
                break;
        }return resID;
    }


            }
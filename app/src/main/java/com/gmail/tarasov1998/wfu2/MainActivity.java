package com.gmail.tarasov1998.wfu2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

    String city = "Dnipro,UA";

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


        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                Intent intent = new Intent(this, StartActivity.class);
                startActivity(intent);
                break;

        }
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

            cityShow.setText(weather.location.getCity());
            temperature.setText("" + Math.round((weather.temperature.getTemp() - 273.15)));
            dateTime.setText(nowDT.toString("dd MMMM yyyy \n HH:mm"));

        }

    }


}
    package com.gmail.tarasov1998.wfu2;

    import android.content.Intent;
    import android.os.AsyncTask;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.DividerItemDecoration;
    import android.support.v7.widget.GridLayoutManager;
    import android.support.v7.widget.RecyclerView;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

    import org.joda.time.LocalDateTime;

    import java.util.ArrayList;
    import java.util.List;


    public class MainActivity extends AppCompatActivity {

        RecycleViewAdapter adapter;
        ArrayList<String> temperatureHours;
        ArrayList<String> time;
        ArrayList<Integer> weatherIcon;
        DividerItemDecoration dividerItemDecoration;
        String city;

        private TextView cityShow;
        private TextView temperature;
        private ImageView mainWeather;


        LocalDateTime date = LocalDateTime.now();


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);



            Intent intent = getIntent();
            city = intent.getStringExtra("city");

            //Set date and time in TextView
            TextView dateTime = (TextView) findViewById(R.id.date);
            dateTime.setText(date.toString("dd MMMM yyyy \n HH:mm"));

            cityShow = (TextView) findViewById(R.id.cityShow);
            temperature = (TextView) findViewById(R.id.temperature);
            mainWeather = (ImageView) findViewById(R.id.mainWeather);



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
                String data = ((new HTTPGet()).getWeatherData(params[0]));

                try {
                    if (data == null) {
                        return null;
                    } else {
                        weather = GetJson.getWeather(data);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                return weather;

            }

            @Override
            protected void onPostExecute(Weather weather) {
                super.onPostExecute(weather);

                if (weather != null) {
                    cityShow.setText(String.format("%s, %s", weather.getCity(), weather.getCountry()));
                    temperature.setText("" + Math.round((weather.getTemp(0) - 273.15)) + "°");
                    mainWeather.setImageResource(weather.choiseIconWeather(weather.getIcon(0)));



                    temperatureHours = new ArrayList<>();
                    setList(temperatureHours, weather);

                    weatherIcon = new ArrayList<>();
                    setWeatherIcon(weatherIcon, weather);

                    time = new ArrayList<>();
                    setList(time, weather);
                    RecyclerView recyclerView = findViewById(R.id.recycleView);


                    int numberOfColumns = 5;
                    recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), numberOfColumns));
                    adapter = new RecycleViewAdapter(getBaseContext(), temperatureHours, weatherIcon, time);

                    recyclerView.setAdapter(adapter);


                } else {
                    Intent intent = new Intent(getApplicationContext(), ActivityStart.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Ничего не найдено", Toast.LENGTH_LONG).show();

                }

            }


        }
        public void setList(List<String> list, Weather weather) {
            if (list == temperatureHours) {
                for (int i = 1; i <= 5; i++) {
                    list.add(Math.round((weather.getTemp(i) - 273.15)) + "°");
                }
            } else if (list == time) {
                for (int i = 1; i <= 5; i++) {
                    list.add(weather.getTime(i).substring(10,16));
                }
            }
        }


        public void setWeatherIcon(List<Integer> list, Weather weather) {
            for (int i = 1; i <= 5; i++) {
                list.add(weather.choiseIconWeather(weather.getIcon(i)));
            }
        }



    }
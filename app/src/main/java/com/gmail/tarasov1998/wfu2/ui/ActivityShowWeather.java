    package com.gmail.tarasov1998.wfu2.ui;

    import android.content.Intent;
    import android.os.AsyncTask;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.GridLayoutManager;
    import android.support.v7.widget.RecyclerView;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.gmail.tarasov1998.wfu2.data.JsonParser;
    import com.gmail.tarasov1998.wfu2.network.HTTPGet;
    import com.gmail.tarasov1998.wfu2.R;
    import com.gmail.tarasov1998.wfu2.model.Weather;

    import org.joda.time.LocalDateTime;

    import java.util.ArrayList;
    import java.util.List;

    import butterknife.BindView;
    import butterknife.ButterKnife;


    public class ActivityShowWeather extends AppCompatActivity {


        ArrayList<String> temperatureHours;
        ArrayList<String> time;
        ArrayList<Integer> weatherIcon;

        RecycleViewAdapter adapter;
        RecyclerView recyclerView;

        private String userCity;
        private int id;


        @BindView(R.id.cityShowWeather)
        TextView cityShow;
        @BindView(R.id.temperatureShowWeather)
        TextView temperature;
        @BindView(R.id.dateShowWeather)
        TextView dateTime;
        @BindView(R.id.weatherShowWeather)
        ImageView Weather;


        private LocalDateTime date = LocalDateTime.now();

        private static final int NUMBER_OF_COLUMNS = 5;
        private static final int FIRST_COLUMN = 1;



        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_weather);
            ButterKnife.bind(this);

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            if (extras != null) {
                userCity = extras.getString("userCity");
                id = extras.getInt("id");
            }

            JSONWeatherTask task = new JSONWeatherTask();
            task.execute(id);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            menu.add(R.string.menu_item);

            return super.onCreateOptionsMenu(menu);
        }

        public boolean onOptionsItemSelected(MenuItem item) {
            Intent intent = new Intent(this, ActivityStart.class);
            startActivity(intent);

            return super.onOptionsItemSelected(item);
        }

        private class JSONWeatherTask extends AsyncTask<Integer, Void, Weather> {

            @Override
            protected Weather doInBackground(Integer... params) {
                Weather weather = new Weather();
                String data = ((new HTTPGet()).getWeatherData(params[0]));

                try {
                    if (data == null) {
                        return null;
                    } else {
                        weather = JsonParser.getWeather(data);
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
                    cityShow.setText(userCity);
                    dateTime.setText(date.toString("dd MMMM yyyy \n HH:mm"));
                    temperature.setText("" + Math.round(weather.getTemp(0)) + "°");
                    Weather.setImageResource(weather.choiseIconWeather(weather.getIcon(0)));



                    temperatureHours = new ArrayList<>();
                    setList(temperatureHours, weather);

                    weatherIcon = new ArrayList<>();
                    setWeatherIcon(weatherIcon, weather);

                    time = new ArrayList<>();
                    setList(time, weather);


                    recyclerView = findViewById(R.id.recycleView);

                    initAdapter();

                } else {
                    Intent intent = new Intent(getApplicationContext(), ActivityStart.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), R.string.err_repeat, Toast.LENGTH_LONG).show();

                }

            }


        }
        public void setList(List<String> list, Weather weather) {
            if (list == temperatureHours) {
                for (int i = FIRST_COLUMN; i <= NUMBER_OF_COLUMNS; i++) {
                    list.add(Math.round(weather.getTemp(i)) + "°");
                }
            } else if (list == time) {
                for (int i = FIRST_COLUMN; i <= NUMBER_OF_COLUMNS; i++) {
                    list.add(weather.getTime(i).substring(10,16));
                }
            }
        }


        public void setWeatherIcon(List<Integer> list, Weather weather) {
            for (int i = FIRST_COLUMN; i <= NUMBER_OF_COLUMNS; i++) {
                list.add(weather.choiseIconWeather(weather.getIcon(i)));
            }
        }
        public void initAdapter(){
            recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), NUMBER_OF_COLUMNS));
            adapter = new RecycleViewAdapter(getBaseContext(), temperatureHours, weatherIcon, time);

            recyclerView.setAdapter(adapter);
        }

    }
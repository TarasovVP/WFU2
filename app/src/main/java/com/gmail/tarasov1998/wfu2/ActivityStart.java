package com.gmail.tarasov1998.wfu2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityStart extends AppCompatActivity {

    ArrayList<HashMap<String, String>> cityCountry;
    List<String> citiesCountries;
    ArrayList<String> cities;
    ArrayList<String> countries;
    HashMap<String, String> setCities;
    EditText editText;
    Button ok;
    String city, result, input, cityRU, transl;
    ListView list;
    ArrayAdapter adapter;
    String cityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        editText = (EditText) findViewById(R.id.editText);
        ok = (Button) findViewById(R.id.ok);
        list = (ListView) findViewById(R.id.list);


    }

    public void onButtonClick(View v) {
        city = editText.getText().toString();
        if (!city.isEmpty()) {
            JSONLocationTask task = new JSONLocationTask();
            task.execute(city);
        }
    }


    private class JSONLocationTask extends AsyncTask<String, Void, Location> {

        @Override
        protected Location doInBackground(String... params) {
            Location location = new Location();
            String loc = ((new HTTPGet()).getLocationData(params[0]));

            try {
                if (loc == null) {
                    return null;
                } else {
                    location = GetJson.getLocation(loc);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return location;

        }

        @Override
        protected void onPostExecute(final Location location) {
            super.onPostExecute(location);

            if (location != null) {
                setCities = new HashMap<>();
                setHash(setCities, location);
                citiesCountries = new ArrayList<>(setCities.keySet());

                cities = new ArrayList<>();
                setList(cities, location);

                countries = new ArrayList<>();
                setList(countries, location);

                cityCountry = new ArrayList<>();
                cityCountry.add(setCities);

                if (location.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Ничего не найдено", Toast.LENGTH_LONG).show();
                } else {
                    list.setChoiceMode(list.CHOICE_MODE_SINGLE);
                    adapter = new ArrayAdapter(getBaseContext(), R.layout.cities_list, citiesCountries);
                    list.setAdapter(adapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            result = location.getUserCity(position);

                            TranslateTask translateTask = new TranslateTask();
                            translateTask.execute(result);

                        }
                    });
                }
            }
        }



         void setList(List<String> list, Location location) {
            int size = location.getCount();
            cityName = location.getUserCity(0);
            for (int i = 0; i < size; i++) {
                list.add(cityName + ", " + location.getUserCountry(i));
            }

        }

         void setHash(HashMap<String, String> hashList, Location location) {
            int size = location.getCount();
            for (int i = 0; i < size; i++) {
                hashList.put(location.getUserCountry(i), location.getUserCity(i));
            }


        }
    }

    private class TranslateTask extends AsyncTask<String, Void, Location> {

        @Override
        protected Location doInBackground(String... params) {
            Location location = new Location();
            Translate translate = new Translate();
            try {
                transl = translate.Post(city);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (transl == null) {
                    return null;
                } else {
                    location = GetJson.getTranslate(transl);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return location;

        }

        @Override
        protected void onPostExecute(final Location location) {
            super.onPostExecute(location);
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("city", result);
            cityRU = location.getCityRU();
            intent.putExtra("cityRU", cityRU);
            startActivity(intent);
        }
    }
}




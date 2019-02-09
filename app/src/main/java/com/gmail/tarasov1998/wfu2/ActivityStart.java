package com.gmail.tarasov1998.wfu2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.neovisionaries.i18n.CountryCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityStart extends AppCompatActivity {

    List<String> countries;
    HashMap<String, Integer> setCities;
    EditText editText;
    Button ok;
    String city, country, countryName, cityName, userCity;
    Integer idCity;
    ListView list;
    ArrayAdapter<String> adapter;


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
                countries = new ArrayList<>(setCities.keySet());


                if (location.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Ничего не найдено", Toast.LENGTH_LONG).show();
                } else {
                    list.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                    adapter = new ArrayAdapter<>(getBaseContext(), R.layout.cities_list, countries);
                    list.setAdapter(adapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            city = location.getUserCity(0);
                            country = countries.get(position);
                            idCity =  setCities.get(country);

                            Intent intent = new Intent(getBaseContext(), ActivityShowWeather.class);
                            Bundle extras = new Bundle();
                            userCity = city + ", " + country;
                            extras.putInt("id", idCity);
                            extras.putString("userCity", userCity);
                            intent.putExtras(extras);
                            startActivity(intent);


                        }
                    });
                }
            }
        }



        void setHash(HashMap<String, Integer> hashList, Location location) {
            cityName = location.getUserCity(0);
            int size = location.getCount();
            for (int i = 0; i < size; i++) {
                countryName = CountryCode.getByCode(location.getUserCountry(i)).getName();
                hashList.put(countryName, location.getId(i));
            }


        }

    }


}




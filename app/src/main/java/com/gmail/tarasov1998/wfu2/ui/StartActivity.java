package com.gmail.tarasov1998.wfu2.ui;

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

import com.gmail.tarasov1998.wfu2.data.JsonParser;
import com.gmail.tarasov1998.wfu2.network.HTTPGet;
import com.gmail.tarasov1998.wfu2.model.Location;
import com.gmail.tarasov1998.wfu2.R;
import com.neovisionaries.i18n.CountryCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity {

    List<String> countries;
    HashMap<String, Integer> setCities;
    Integer idCity;
    String city, country, countryName, cityName, userCity;

    @BindView(R.id.editTextStart)
    EditText editText;
    @BindView(R.id.buttonOkStart)
    Button ok;

    @BindView(R.id.listCitiesStart)
    ListView list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        ButterKnife.bind(this);
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
                    location = JsonParser.getLocation(loc);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return location;

        }

        @Override
        protected void onPostExecute(final Location location) {
            super.onPostExecute(location);

            if (location == null) {
                Toast.makeText(getApplicationContext(), R.string.err_repeat, Toast.LENGTH_LONG).show();
            } else {
                if (location.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), R.string.err_nothing_found, Toast.LENGTH_LONG).show();
                } else {
                    setCities = new HashMap<>();
                    setHash(setCities, location);
                    countries = new ArrayList<>(setCities.keySet());

                    list.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                    adapter = new ArrayAdapter<>(getBaseContext(), R.layout.cities_list, countries);
                    list.setAdapter(adapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            city = location.getUserCity(0);
                            country = countries.get(position);
                            idCity = setCities.get(country);

                            Intent intent = new Intent(getBaseContext(), ShowWeatherActivity.class);
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




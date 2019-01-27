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
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class ActivityStart extends AppCompatActivity {

    ArrayList<String> cities;
    ArrayList<String> countries;
    EditText editText;
    Button ok;
    String city, result, input;
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
        String data = ((new HTTPGet()).getLocationData(params[0]));

        try {
            if (data == null) {
                return null;
            } else {
                location = GetJson.getLocation(data);
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
            cities = new ArrayList<>();
            setList(cities, location);

            countries = new ArrayList<>();
            setList(countries, location);

            if (location.getCount() == 0) {
                Toast.makeText(getApplicationContext(), "Ничего не найдено", Toast.LENGTH_LONG).show();
            } else {
                list.setChoiceMode(list.CHOICE_MODE_SINGLE);
                adapter = new ArrayAdapter<>(getBaseContext(), R.layout.cities_list, cities);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        result = location.getUserCity(position) + ", " + location.getUserCountry(position);
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        intent.putExtra("city", result);
                        startActivity(intent);

                    }
                });
            }
        }
    }



    public void setList(List<String> list, Location location) {
        int size = location.getCount();
        for (int i = 0; i < size; i++) {
            list.add(location.getUserCity(i) + ", " + location.getUserCountry(i));
        }

    }
}
}
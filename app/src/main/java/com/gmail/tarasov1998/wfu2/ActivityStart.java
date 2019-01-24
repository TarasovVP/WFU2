package com.gmail.tarasov1998.wfu2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityStart extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> cities;
    ArrayList<String> countries;
    EditText editText;
    Button ok;
    String city, result;
    ListView list;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        editText = (EditText) findViewById(R.id.editText);
        ok = (Button) findViewById(R.id.ok);
        list = (ListView) findViewById(R.id.list);


        JSONLocationTask task = new JSONLocationTask();
        task.execute(city);

        list.setChoiceMode(list.CHOICE_MODE_SINGLE);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, cities);
        list.setAdapter(adapter);

    }

    public void onButtonClick(View v) {
        city = editText.getText().toString();
        if (!city.isEmpty()) {

        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("city", result);
        startActivity(intent);
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
        protected void onPostExecute(Location location) {
            super.onPostExecute(location);

            if (location != null) {
                cities = new ArrayList<>();
                setList(cities, location);

                countries = new ArrayList<>();
                setList(countries, location);
            } else {

                Toast.makeText(getApplicationContext(), "Ничего не найдено", Toast.LENGTH_LONG).show();

            }

        }


    }

    public void setList(List<String> list, Location location) {
        if (list == cities) {
            for (int i = 1; i <= 5; i++) {
                list.add(location.getUserCity(i));
            }
        } else if (list == countries) {
            for (int i = 1; i <= 5; i++) {
                list.add(location.getUserCountry(i));
            }
        }
    }
}

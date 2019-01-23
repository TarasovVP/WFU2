package com.gmail.tarasov1998.wfu2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityStart extends AppCompatActivity {

    EditText editText;
    Button ok;
    String city, result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        editText = (EditText) findViewById(R.id.editText);
        ok = (Button) findViewById(R.id.ok);


    }

    public void onButtonClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        Location location = new Location();
        GetLocation getLocation = new GetLocation();
        city = editText.getText().toString();
        if (!city.isEmpty()) {
           HTMLLocationTask task = new HTMLLocationTask();
           task.execute(city);
        }
        intent.putExtra("city", result);
        startActivity(intent);
    }
    private class HTMLLocationTask extends AsyncTask<String, Void, Location> {

        @Override
        protected Location doInBackground(String... params) {
            Location location = new Location();
            String data = ((new HTTPGet()).getLocationData(params[0]));

            try {
                if (data == null) {
                    return null;
                } else {
                    location = GetLocation.getLocation(data);
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


            } else {

                Toast.makeText(getApplicationContext(), "Ничего не найдено", Toast.LENGTH_LONG).show();

            }

        }


    }
}

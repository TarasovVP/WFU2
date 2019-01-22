package com.gmail.tarasov1998.wfu2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        city = editText.getText().toString();
        if (!city.isEmpty()) {

         result = location.getUserCity() + "," + location.getUserCountry();
        }
        intent.putExtra("city", result);
        startActivity(intent);
    }

}

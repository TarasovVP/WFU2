package com.gmail.tarasov1998.wfu2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.gmail.tarasov1998.wfu2.R.color.White;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout layoutStart;
    TextView choise;
    EditText editText;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        layoutStart = (RelativeLayout) findViewById(R.id.layoutStart);

        ok = (Button) findViewById(R.id.ok);

        ok.setOnClickListener(this);

            }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok:
                if (editText.getText().toString().equals("Dnipro")) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
        }

    }
}
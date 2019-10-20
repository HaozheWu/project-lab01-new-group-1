package com.example.wassimelahmar.tutorial03;

import android.hardware.display.DisplayManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {

    private EditText teamName = (EditText) findViewById(R.id.teamNameField);
    private EditText zipCode = (EditText) findViewById(R.id.ZIPcodeField);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickImageView(View v){

    }

    public void onClickOpenMap(View v){

    }


}

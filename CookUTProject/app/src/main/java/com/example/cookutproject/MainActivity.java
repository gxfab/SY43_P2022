package com.example.cookutproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeActivitySaisie(View view){
        Intent intent = new Intent(this, Saisie.class);
        startActivity(intent);
    }
    public void changeActivityRecap(View view){
        Intent intent = new Intent(this, Recap.class);
        startActivity(intent);
    }
    public void changeActivityEvent(View view){
        Intent intent = new Intent(this, Event.class);
        startActivity(intent);
    }
}
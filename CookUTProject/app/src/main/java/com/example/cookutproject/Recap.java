package com.example.cookutproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cookutproject.MainActivity;
import com.example.cookutproject.R;
import com.example.cookutproject.models.Master;


public class Recap extends AppCompatActivity {

    private LinearLayout myLayout;
    private Master master;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recap);


    }
    public void changeActivityMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
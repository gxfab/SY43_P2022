package com.example.cookutproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Recap extends AppCompatActivity {

    private LinearLayout myLayout;
    private Master master;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recap);

        this.myLayout = findViewById(R.id.recapLayout);


        TextView textRepas = new TextView(this);
        textRepas.setText("Repas");
        textRepas.setTextSize(50);
        textRepas.setBackgroundColor(getResources().getColor(R.color.darkgreen_CookUT));
        textRepas.setTextColor(getResources().getColor(R.color.white));

        textRepas.setGravity(View.SCROLL_AXIS_HORIZONTAL);
        myLayout.addView(textRepas);
    }
    public void changeActivityMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
package com.example.zerobudbanker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }

    public void goToAddDetail(View view) {
        Intent myIntent = new Intent(this, AddDetailActivity.class);
        startActivity(myIntent);
    }

    public void goToAdd(View view ) {
        Intent myIntent = new Intent(this, AddActivity.class);
        startActivity(myIntent);
    }

    public void goToProfile(View view ) {
        Intent myIntent = new Intent(this, ProfileChoiceActivity.class);
        startActivity(myIntent);
    }

    public void goToStats(View view ) {
        Intent myIntent = new Intent(this, StatsActivity.class);
        startActivity(myIntent);
    }

    public void goToHome(View view ) {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    public void categoryChoice(View view) {
        Intent i2 = getIntent();
        int value = i2.getIntExtra("MENU",-1);

        Intent myIntent;
        Button b = (Button)view;
        if (value == 2){
            myIntent = new Intent(this, ProfileActivity.class);
        } else {
            myIntent = new Intent(this, AddDetailActivity.class);
        }
        String choice = b.getText().toString();
        myIntent.putExtra("CHOICE", choice);
        myIntent.putExtra("CHOICE_LIST", i2.getStringExtra("LIST"));
        startActivity(myIntent);
    }
}
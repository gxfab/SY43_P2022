package com.example.zerobudbanker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void goToAdd(View view ) {
        Intent myIntent = new Intent(this, AddActivity.class);
        startActivity(myIntent);
    }

    public void goToProfile(View view ) {
        Intent myIntent = new Intent(this, ProfileActivity.class);
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

    public void goToAddDetail(View view ) {
        Intent myIntent = new Intent(this, AddDetailActivity.class);
        startActivity(myIntent);
    }
}
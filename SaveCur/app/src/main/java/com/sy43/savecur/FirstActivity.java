package com.sy43.savecur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSignInBtnClicked(android.view.View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
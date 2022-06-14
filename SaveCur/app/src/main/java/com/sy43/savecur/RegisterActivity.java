package com.sy43.savecur;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onRegisterBtnClicked(android.view.View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
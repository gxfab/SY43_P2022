package com.example.nomoola.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.nomoola.R;
import com.example.nomoola.viewModel.CategoryViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("CREATION", "onCreate from " + this.getClass().toString() + " started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);

        Log.d("CREATION", "onCreate from " + this.getClass().toString() + " finished");
    }
}
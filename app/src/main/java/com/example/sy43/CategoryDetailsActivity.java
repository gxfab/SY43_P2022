package com.example.sy43;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CategoryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Category details");

        setContentView(R.layout.activity_category_details);
    }
}
package com.example.sy43;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;


public class Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Category page");
        ArrayList<String> categories = new ArrayList<String>();
        categories.add("Food");
        categories.add("Voyage");
        categories.add("Etude");
        categories.add("Taxes");
        setContentView(R.layout.activity_category);
    }
}
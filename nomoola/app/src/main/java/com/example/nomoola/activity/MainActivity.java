package com.example.nomoola.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.nomoola.R;
import com.example.nomoola.viewModel.CategoryViewModel;

public class MainActivity extends AppCompatActivity {

    private CategoryViewModel mCatViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mCatViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
    }
}
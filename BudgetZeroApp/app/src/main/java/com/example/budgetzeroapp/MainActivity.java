package com.example.budgetzeroapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.example.budgetzeroapp.databinding.ActivityMainBinding;


public class MainActivity extends OptionsMenu {
    private Toolbar toolbar;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.Home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.Budget:
                    replaceFragment(new BudgetFragment());
                    break;
                case R.id.CashFlow:
                    replaceFragment(new CashFlowFragment());
                    break;
                case R.id.Savings:
                    replaceFragment(new SavingsFragment());
                    break;
            }

            return true;
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected void replaceFragment(Fragment frag){
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.frame_layout,frag);
        fragTransaction.commit();
    }
}
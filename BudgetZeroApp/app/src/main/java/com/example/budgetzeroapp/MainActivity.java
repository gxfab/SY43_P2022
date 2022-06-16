package com.example.budgetzeroapp;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.budgetzeroapp.fragment.SavingsFragment;
import com.example.budgetzeroapp.tool.OptionsMenu;
import com.example.budgetzeroapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends OptionsMenu {
    private static MainActivity activity;
    private BottomNavigationView bottomNavigationView;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        activity = this;

        /**Navigation**/
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

    public void bottomNavigationRedirect(int id)
    {
        bottomNavigationView.setSelectedItemId(id);
    }

    public void selectSavingsTab(int tab_id)
    {
        SavingsFragment.selectPage(tab_id);
    }

    public static MainActivity getActivity() {
        return activity;
    }
}
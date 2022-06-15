package com.example.budgetzeroapp;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.budgetzeroapp.fragment.BudgetFragment;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.fragment.cashflow.CashFlowFragment;
import com.example.budgetzeroapp.fragment.HomeFragment;
import com.example.budgetzeroapp.fragment.savings.SavingsFragment;
import com.example.budgetzeroapp.tool.OptionsMenu;
import com.example.budgetzeroapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends OptionsMenu {
    private ActivityMainBinding binding;
    private static MainActivity activity;
    private LayoutInflater inflater;
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


    public void bottomNavigationRedirect(int id){
        bottomNavigationView.setSelectedItemId(id);
    }

    public static MainActivity getActivity() {
        return activity;
    }
}
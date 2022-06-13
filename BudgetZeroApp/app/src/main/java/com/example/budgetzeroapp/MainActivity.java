package com.example.budgetzeroapp;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.budgetzeroapp.fragment.BudgetFragment;
import com.example.budgetzeroapp.fragment.cashflow.CashFlowFragment;
import com.example.budgetzeroapp.fragment.HomeFragment;
import com.example.budgetzeroapp.fragment.savings.SavingsFragment;
import com.example.budgetzeroapp.tool.OptionsMenu;
import com.example.budgetzeroapp.databinding.ActivityMainBinding;


public class MainActivity extends OptionsMenu {
    private Toolbar toolbar;
    private ActivityMainBinding binding;
    private static MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        /**Menu**/
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.Home:
                    replaceFragment(new HomeFragment());
                    View childLayout = inflater.inflate(R.layout.toolbar_home, (ViewGroup)findViewById(R.id.toolbar_home));
                    LinearLayout appbar = (LinearLayout) findViewById(R.id.appBarLayout);
                    appbar.removeAllViewsInLayout();
                    appbar.addView(childLayout);
                    break;
                case R.id.Budget:
                    replaceFragment(new BudgetFragment());
                    View childLayout2 = inflater.inflate(R.layout.toolbar_budget, (ViewGroup)findViewById(R.id.toolbar_budget));
                    LinearLayout appbar2 = (LinearLayout) findViewById(R.id.appBarLayout);
                    appbar2.removeAllViewsInLayout();
                    appbar2.addView(childLayout2);
                    break;
                case R.id.CashFlow:
                    replaceFragment(new CashFlowFragment());
                    View childLayout3 = inflater.inflate(R.layout.toolbar_cashflow, (ViewGroup)findViewById(R.id.toolbar_cashflow));
                    LinearLayout appbar3 = (LinearLayout) findViewById(R.id.appBarLayout);
                    appbar3.removeAllViewsInLayout();
                    appbar3.addView(childLayout3);
                    break;
                case R.id.Savings:
                    replaceFragment(new SavingsFragment());
                    View childLayout4 = inflater.inflate(R.layout.toolbar_savings, (ViewGroup)findViewById(R.id.toolbar_savings));
                    LinearLayout appbar4 = (LinearLayout) findViewById(R.id.appBarLayout);
                    appbar4.removeAllViewsInLayout();
                    appbar4.addView(childLayout4);
                    break;
            }

            return true;
        });

    }

    public void replaceFragment(Fragment frag){
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.frame_layout,frag);
        fragTransaction.commit();
    }

    public static MainActivity getActivity() {
        return activity;
    }
}
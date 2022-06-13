package com.example.budgetzeroapp;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        /**Menu**/
        inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            updateUpperToolBar(item.getItemId());
            return true;
        });
    }


    public void updateUpperToolBar(int id){
        View childLayout;
        if(id == R.id.Home){
            replaceFragment(new HomeFragment());
            childLayout = inflater.inflate(R.layout.toolbar_home, (ViewGroup)findViewById(R.id.toolbar_home));
        }else if(id == R.id.Budget){
            replaceFragment(new BudgetFragment());
            childLayout = inflater.inflate(R.layout.toolbar_budget, (ViewGroup)findViewById(R.id.toolbar_budget));
        }else if(id == R.id.CashFlow){
            replaceFragment(new CashFlowFragment());
            childLayout = inflater.inflate(R.layout.toolbar_cashflow, (ViewGroup)findViewById(R.id.toolbar_cashflow));
        }else{
            replaceFragment(new SavingsFragment());
            childLayout = inflater.inflate(R.layout.toolbar_savings, (ViewGroup)findViewById(R.id.toolbar_savings));
        }
        LinearLayout appbar = (LinearLayout) findViewById(R.id.appBarLayout);
        appbar.removeAllViewsInLayout();
        appbar.addView(childLayout);
    }

    public void bottomNavigationRedirect(int id){
        binding.bottomNavigationView.setSelectedItemId(id);
    }

    public void replaceFragment(DataBaseFragment frag){
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.frame_layout,frag);
        fragTransaction.commit();
        //frag.getToolBarID();
    }

    public static MainActivity getActivity() {
        return activity;
    }
}
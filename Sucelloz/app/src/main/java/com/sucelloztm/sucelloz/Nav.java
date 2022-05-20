package com.sucelloztm.sucelloz;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sucelloztm.sucelloz.databinding.ActivityNavigationBinding;
import com.sucelloztm.sucelloz.view.HomeScreen;
import com.sucelloztm.sucelloz.view.SavingsScreen;
import com.sucelloztm.sucelloz.view.SpendingsScreen;
import com.sucelloztm.sucelloz.view.ZeroBudgetScreen;

public class Nav extends AppCompatActivity {

    private ActivityNavigationBinding binding;
    private Fragment myFragment;

    public static HomeScreen HS = new HomeScreen();
    public static SavingsScreen SaS = new SavingsScreen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(HS);
        BottomNavigationView bottomNav = findViewById(R.id.nav_view);

        bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.homeScreen:
                    if (savedInstanceState != null){
                        HS.setArguments(savedInstanceState);
                    }
                    replaceFragment(HS);
                    break;

                case R.id.spendingsScreen:
                    replaceFragment(new SpendingsScreen());
                    break;

                case R.id.savingsScreen:
                    if(savedInstanceState!=null){
                        SaS.setArguments(savedInstanceState);
                    }
                    replaceFragment(SaS);
                    break;

                case R.id.zeroBudgetScreen:
                    replaceFragment(new ZeroBudgetScreen());
                    break;
                default:
                    replaceFragment(HS);
                    break;
            }
            return true;
        });

        if (savedInstanceState != null){
            myFragment = getSupportFragmentManager().getFragment(savedInstanceState, "HomeScreen");
        }
    }
    @Override
    public void onSaveInstanceState(Bundle i){
        super.onSaveInstanceState(i);
        getSupportFragmentManager().putFragment(i,"HomeScreen", myFragment);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_navigation,fragment);
        fragmentTransaction.commit();
    }
}
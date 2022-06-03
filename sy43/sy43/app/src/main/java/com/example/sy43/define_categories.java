package com.example.sy43;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class define_categories extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    boolean addToBackStack;
    CardView continue_home;
    LinearLayout clothing_cat, communication_cat, entertainment_cat, finance_cat, food_cat, health_cat, housing_cat, transport_cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_categories);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction= fragmentManager.beginTransaction();
        addToBackStack=false;
        /*SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE );

        String firstTime = sharedPreferences.getString("First Time Install","");

        if(firstTime.equals("Yes")){
            Intent intent = new Intent(define_categories.this, define_categories.class);
            startActivity(intent);
        }
        else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("First Time Install", "Yes");
            editor.apply();
        }*/

        toHome();
        popupClothes();
        popupCommunication();
        popupEntertainment();
        popupFinance();
        popupFood();
        popupHealth();
        popupHousing();
        popupTransport();
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    private void toHome(){
        continue_home = findViewById(R.id.continue_to_home_btn);
        continue_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(define_categories.this, Home.class));
            }
        });
    }

    private void popupClothes(){
        clothing_cat=findViewById(R.id.clothing_cat);
        clothing_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clothing clothing = new clothing();
                clothing.show(getSupportFragmentManager(), "Clothes Sub Categories");
            }
        });
    }

    private void popupCommunication(){
        communication_cat=findViewById(R.id.communication_cat);
        communication_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communication communication = new communication();
                communication.show(getSupportFragmentManager(), "Communication Sub Categories");
            }
        });
    }

    private void popupEntertainment(){
        entertainment_cat=findViewById(R.id.entertainment_cat);
        entertainment_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entertainment entertainment = new entertainment();
                entertainment.show(getSupportFragmentManager(), "Entertainment Sub Categories");
            }
        });
    }

    private void popupFinance(){
        finance_cat =findViewById(R.id.finance_cat);
        finance_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finance finance = new finance();
                finance.show(getSupportFragmentManager(), "Finance Sub Categories");
            }
        });
    }

    private void popupFood(){
        food_cat=findViewById(R.id.food_cat);
        food_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food food = new food();
                food.show(getSupportFragmentManager(), "Food Sub Categories");
            }
        });
    }

    private void popupHealth(){
        health_cat=findViewById(R.id.health_cat);
        health_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                health health = new health();
                health.show(getSupportFragmentManager(), "Health Sub Categories");
            }
        });
    }

    private void popupHousing(){
        housing_cat=findViewById(R.id.housing_cat);
        housing_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                housing housing = new housing();
                housing.show(getSupportFragmentManager(), "Housing Sub Categories");
            }
        });
    }

    private void popupTransport(){
        transport_cat=findViewById(R.id.transport_cat);
        transport_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transportation transportation = new transportation();
                transportation.show(getSupportFragmentManager(), "Transportation Sub Categories");
            }
        });
    }

    public void addFragmentBackStack(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commitAllowingStateLoss();
    }
}

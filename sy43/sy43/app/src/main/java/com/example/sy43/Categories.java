package com.example.sy43;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Arrays;

public class Categories extends AppCompatActivity {

    Spinner sp_categories, sp_subcategories;
    ArrayAdapter<String> arrayAdapter_categories, arrayAdapter_subcategories;
    ArrayList<String> arrayList_categories, arrayList_Communication, arrayList_Clothing, arrayList_Entertainment, arrayList_Finance, arrayList_Food, arrayList_Health, arrayList_Housing, arrayList_Transport;
    ImageView house_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        house_icon= findViewById(R.id.home_icon);
        house_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                housing housingFragment = new housing();
                housingFragment.show(getSupportFragmentManager(), "Housing Sub Categories");
            }
        });

        toHome();

        /*sp_categories = findViewById(R.id.add_expense_category);
        sp_subcategories = findViewById(R.id.add_expense_subcategory);

        arrayList_categories = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Categories_Array)));
        arrayList_Clothing = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Clothing_Sub)));
        arrayList_Communication = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Communication_Sub)));
        arrayList_Housing = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Housing_Sub)));
        arrayList_Entertainment = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Entertainment_Sub)));
        arrayList_Finance = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Finance_Sub)));
        arrayList_Food = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Food_Sub)));
        arrayList_Transport = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Transport_Sub)));
        arrayList_Health = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Health_Sub)));

        arrayAdapter_categories = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList_categories);
        sp_categories.setAdapter(arrayAdapter_categories);

        sp_categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    arrayAdapter_subcategories = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList_Clothing);
                }
                if(position==1){
                    arrayAdapter_subcategories = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList_Communication);
                }
                if(position==2){
                    arrayAdapter_subcategories = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList_Entertainment);
                }
                if(position==3){
                    arrayAdapter_subcategories = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList_Finance);
                }
                if(position==4){
                    arrayAdapter_subcategories = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList_Food);
                }
                if(position==5){
                    arrayAdapter_subcategories = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList_Health);
                }
                if(position==6){
                    arrayAdapter_subcategories = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList_Housing);
                }
                if(position==6){
                    arrayAdapter_subcategories = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList_Transport);
                }

                sp_subcategories.setAdapter(arrayAdapter_subcategories);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

    }

    private void toHome(){
        ImageView home_icon = findViewById(R.id.home_icon);
        home_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Categories.this, Home.class));
            }
        });
    }
}
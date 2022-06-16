package com.example.sy43;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.sy43.database.AppDatabase;
import com.example.sy43.database.AppDatabase_Impl;
import com.example.sy43.database.Category;
import com.example.sy43.database.MonthlyRevenue;
import com.example.sy43.database.SubCategory;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Categories extends AppCompatActivity {


   //Spinner sp_categories, sp_subcategories;
    //ArrayAdapter<String> arrayAdapter_categories, arrayAdapter_subcategories;
    //ArrayList<String> arrayList_categories, arrayList_Communication, arrayList_Clothing, arrayList_Entertainment, arrayList_Finance, arrayList_Food, arrayList_Health, arrayList_Housing, arrayList_Transport;
    ImageView house_icon;
    TextView title, amount;
    //RecyclerView recyclerView;

    private RecyclerView recyclerView;
    private List<DataModel2> mList;
    private CategoryAdapter adapter;
    double sum =0;
    TextView subcategory_name, subcategory_amount;
    List<SubCategory> subCategory;
    List<String> subcategories_titles  ;
    List<String> subcategories_enveloppes;
    CardView add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        AppDatabase db = AppDatabase.getInstance(this);

        List<Category> categories = db.categoryDao().getAll();

        Spinner period = findViewById(R.id.spinner2);
        String[] monthsArray = getResources().getStringArray(R.array.Months_Array);
        List<String> months = new ArrayList<String>();
        for (MonthlyRevenue mth: db.monthlyRevenueDao().getAll()) {
            months.add(Month.of(mth.month).getDisplayName(TextStyle.FULL, Locale.ENGLISH));
            //months.add(mth);
        }
        ArrayAdapter<String> monthData = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, months);
        monthData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        period.setAdapter(monthData);

        period.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {generateList(categories,db);}
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}});

        house_icon= findViewById(R.id.home_icon);
        toHome();
        toOverview();

        subcategory_name = findViewById(R.id.subcategoryTV);
        subcategory_amount = findViewById(R.id.subcategoryEnveloppe);

        recyclerView = findViewById(R.id.main_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        add_btn = findViewById(R.id.add_new_subcategory);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_category add_category = new add_category();
                add_category.show(getSupportFragmentManager(), "List of undefined Sub Categories");
            }
        });

        generateList(categories,db);

//        mList = new ArrayList<>();
//        for (int i=0; i<categories.size(); i++){
//            sum=0;
//            subCategory = db.subCategoryDao().findByCategory(i+1);
//            subcategories_titles= new ArrayList<>();
//            subcategories_enveloppes= new ArrayList<>();
//            for (int j=0; j<subCategory.size();j++){
//                subcategories_titles.add(subCategory.get(j).name);
//                subcategories_enveloppes.add(Double.toString(subCategory.get(j).envelope));
//                sum+= subCategory.get(j).envelope;
//            }
//
//            switch (categories.get(i).name) {
//                case "Clothing" :
//                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.clothing),Double.toString(sum) ));
//                    break;
//                case "Communication" :
//                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.phone),Double.toString(sum) ));
//                    break;
//                case "Entertainment" :
//                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.entertainment),Double.toString(sum) ));
//                    break;
//                case "Finance" :
//                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.finance),Double.toString(sum) ));
//                    break;
//                case "Food" :
//                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.food),Double.toString(sum) ));
//                    break;
//                case "Health" :
//                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.health),Double.toString(sum) ));
//                    break;
//                case "Housing" :
//                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.house),Double.toString(sum) ));
//                    break;
//                case "Transport" :
//                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.transport),Double.toString(sum) ));
//            }
//        }

        adapter = new CategoryAdapter(mList);
        recyclerView.setAdapter(adapter);

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

    private void toOverview(){
        ImageView overview = findViewById(R.id.overview_icon);
        overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Categories.this, Overview.class));
            }
        });
    }

    private void generateList(List<Category> categories, AppDatabase db){
        mList = new ArrayList<>();
        for (int i=0; i<categories.size(); i++){
            sum=0;
            subCategory = db.subCategoryDao().findByCategory(i+1);
            subcategories_titles= new ArrayList<>();
            subcategories_enveloppes= new ArrayList<>();
            for (int j=0; j<subCategory.size();j++){
                subcategories_titles.add(subCategory.get(j).name);
                subcategories_enveloppes.add(Double.toString(subCategory.get(j).envelope));
                sum+= subCategory.get(j).envelope;
            }

            switch (categories.get(i).name) {
                case "Clothing" :
                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.clothing),Double.toString(sum) ));
                    break;
                case "Communication" :
                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.phone),Double.toString(sum) ));
                    break;
                case "Entertainment" :
                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.entertainment),Double.toString(sum) ));
                    break;
                case "Finance" :
                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.finance),Double.toString(sum) ));
                    break;
                case "Food" :
                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.food),Double.toString(sum) ));
                    break;
                case "Health" :
                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.health),Double.toString(sum) ));
                    break;
                case "Housing" :
                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.house),Double.toString(sum) ));
                    break;
                case "Transport" :
                    mList.add(new DataModel2(subcategories_titles, subcategories_enveloppes,categories.get(i).name,  ContextCompat.getDrawable(this,R.drawable.transport),Double.toString(sum) ));
            }
        }
    }
}










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







/*
        List<Drawable> clothingimg = new ArrayList<>();
        clothingimg.add(ContextCompat.getDrawable(this,R.drawable.accessories));
        clothingimg.add(ContextCompat.getDrawable(this,R.drawable.clothes));
        clothingimg.add(ContextCompat.getDrawable(this,R.drawable.footwear));
        allimgs.add(clothingimg);

        List<Drawable> communicationimg= new ArrayList<>();
        communicationimg.add(ContextCompat.getDrawable(this, R.drawable.internet));
        communicationimg.add(ContextCompat.getDrawable(this, R.drawable.phone));
        communicationimg.add(ContextCompat.getDrawable(this, R.drawable.tv));
        allimgs.add(communicationimg);


        List<Drawable> entertainmentimg = new ArrayList<>();
        entertainmentimg.add(ContextCompat.getDrawable(this,R.drawable.books));
        entertainmentimg.add(ContextCompat.getDrawable(this,R.drawable.events));
        entertainmentimg.add(ContextCompat.getDrawable(this,R.drawable.gaming));
        entertainmentimg.add(ContextCompat.getDrawable(this,R.drawable.gift));
        entertainmentimg.add(ContextCompat.getDrawable(this,R.drawable.hangout));
        entertainmentimg.add(ContextCompat.getDrawable(this,R.drawable.hotel));
        entertainmentimg.add(ContextCompat.getDrawable(this,R.drawable.multimedia));
        entertainmentimg.add(ContextCompat.getDrawable(this,R.drawable.pet));
        entertainmentimg.add(ContextCompat.getDrawable(this,R.drawable.sports));
        entertainmentimg.add(ContextCompat.getDrawable(this,R.drawable.vacation));
        allimgs.add(entertainmentimg);

        List<Drawable> financeimg = new ArrayList<>();
        financeimg.add(ContextCompat.getDrawable(this,R.drawable.banking));
        financeimg.add(ContextCompat.getDrawable(this,R.drawable.loan));
        financeimg.add(ContextCompat.getDrawable(this,R.drawable.salarytax));
        allimgs.add(financeimg);

        List<Drawable> foodimg = new ArrayList<>();
        foodimg.add(ContextCompat.getDrawable(this,R.drawable.canteen));
        foodimg.add(ContextCompat.getDrawable(this,R.drawable.groceries));
        foodimg.add(ContextCompat.getDrawable(this,R.drawable.restaurant));
        allimgs.add(foodimg);

        List<Drawable> healthimg = new ArrayList<>();
        healthimg.add(ContextCompat.getDrawable(this, R.drawable.barber));
        healthimg.add(ContextCompat.getDrawable(this,R.drawable.bodycare));
        healthimg.add(ContextCompat.getDrawable(this,R.drawable.doctor));
        healthimg.add(ContextCompat.getDrawable(this,R.drawable.life_insurance));
        healthimg.add(ContextCompat.getDrawable(this,R.drawable.pharmacy));
        allimgs.add(healthimg);

        List<Drawable> housingimg = new ArrayList<>();
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.cleaning));
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.furniture));
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.garden));
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.house_insurance));
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.rent));
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.repairs));
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.taxes));
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.utilities));
        allimgs.add(housingimg);

        List<Drawable> transportimg = new ArrayList<>();
        transportimg.add(ContextCompat.getDrawable(this,R.drawable.car_insurance));
        transportimg.add(ContextCompat.getDrawable(this,R.drawable.car_fees));
        transportimg.add(ContextCompat.getDrawable(this,R.drawable.fuel));
        transportimg.add(ContextCompat.getDrawable(this,R.drawable.parking));
        transportimg.add(ContextCompat.getDrawable(this,R.drawable.tolls));
        transportimg.add(ContextCompat.getDrawable(this,R.drawable.transportation));
        allimgs.add(transportimg);
*/
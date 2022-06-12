package com.example.sy43;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sy43.database.SubCategory;
import com.example.sy43.database.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class Define_categories extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    CardView continue_home, apply_btn;
    EditText expected_expense;
    TextView subcategory_name;
    ImageView subcategory_image;

    private ViewModel viewModel;
    private RecyclerView recyclerView;
    private List<DataModel> mList;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_categories);

        viewModel =  new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getAllSubCategories().observe(this, new Observer<List<SubCategory>>() {
            @Override
            public void onChanged(List<SubCategory> subCategories) {
                // triggered everytime data in our LiveData changes
                //update the recyclerview
                Toast.makeText(Define_categories.this, "...", Toast.LENGTH_SHORT).show();
            }
        });
        toIncome();

        apply_btn = findViewById(R.id.apply_subcategory);
        expected_expense = findViewById(R.id.expected_expense);
        subcategory_image = findViewById(R.id.subcategory_imageview);
        subcategory_name = findViewById(R.id.nestedItemTv);

        recyclerView = findViewById(R.id.main_recyclervie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mList = new ArrayList<>();

        List<String> clothing = new ArrayList<>();
        clothing.add("Accessories");
        clothing.add("Clothes");
        clothing.add("Footwear");

        List<Drawable> clothingimg = new ArrayList<>();
        clothingimg.add(ContextCompat.getDrawable(this,R.drawable.accessories));
        clothingimg.add(ContextCompat.getDrawable(this,R.drawable.clothes));
        clothingimg.add(ContextCompat.getDrawable(this,R.drawable.footwear));

        List<CardView> clothingbtns = new ArrayList<>();
        clothingbtns.add( new CardView(this));
        clothingbtns.add(new CardView(this));
        clothingbtns.add(new CardView(this));

        List<EditText> clothinget = new ArrayList<>();
        clothinget.add(new EditText(this));
        clothinget.add(new EditText(this));
        clothinget.add(new EditText(this));

        List<String> communication = new ArrayList<>();
        communication.add("Internet");
        communication.add("Phone");
        communication.add("Television");

        List<Drawable> communicationimg= new ArrayList<>();
        communicationimg.add(ContextCompat.getDrawable(this, R.drawable.internet));
        communicationimg.add(ContextCompat.getDrawable(this, R.drawable.phone));
        communicationimg.add(ContextCompat.getDrawable(this, R.drawable.tv));

        List<CardView> communicationbtns = new ArrayList<>();
        communicationbtns.add( new CardView(this));
        communicationbtns.add(new CardView(this));
        communicationbtns.add(new CardView(this));

        List<EditText> communicationet = new ArrayList<>();
        communicationet.add(new EditText(this));
        communicationet.add(new EditText(this));
        communicationet.add(new EditText(this));


        List<String> entertainment = new ArrayList<>();
        entertainment.add("Books");
        entertainment.add("Events");
        entertainment.add("Gaming");
        entertainment.add("Gift");
        entertainment.add("Hangouts");
        entertainment.add("Hotel");
        entertainment.add("Multimedia");
        entertainment.add("Pet");
        entertainment.add("Sports");
        entertainment.add("Vacation");

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

        List<CardView> entertainmentbtns = new ArrayList<>();
        entertainmentbtns.add( new CardView(this));
        entertainmentbtns.add( new CardView(this));
        entertainmentbtns.add( new CardView(this));
        entertainmentbtns.add( new CardView(this));
        entertainmentbtns.add( new CardView(this));
        entertainmentbtns.add( new CardView(this));
        entertainmentbtns.add( new CardView(this));
        entertainmentbtns.add( new CardView(this));
        entertainmentbtns.add( new CardView(this));
        entertainmentbtns.add( new CardView(this));

        List<EditText> entertainmentet = new ArrayList<>();
        entertainmentet.add(new EditText(this));
        entertainmentet.add(new EditText(this));
        entertainmentet.add(new EditText(this));
        entertainmentet.add(new EditText(this));
        entertainmentet.add(new EditText(this));
        entertainmentet.add(new EditText(this));
        entertainmentet.add(new EditText(this));
        entertainmentet.add(new EditText(this));
        entertainmentet.add(new EditText(this));
        entertainmentet.add(new EditText(this));


        List<String> finance = new ArrayList<>();
        finance.add("Banking Fees");
        finance.add("Loan");
        finance.add("Salary tax");

        List<Drawable> financeimg = new ArrayList<>();
        financeimg.add(ContextCompat.getDrawable(this,R.drawable.banking));
        financeimg.add(ContextCompat.getDrawable(this,R.drawable.loan));
        financeimg.add(ContextCompat.getDrawable(this,R.drawable.salarytax));

        List<CardView> financebtns = new ArrayList<>();
        financebtns.add(new CardView(this));
        financebtns.add(new CardView(this));
        financebtns.add(new CardView(this));

        List<EditText> financeet = new ArrayList<>();
        financeet.add(new EditText(this));
        financeet.add(new EditText(this));
        financeet.add(new EditText(this));


        List<String> food = new ArrayList<>();
        food.add("Canteen");
        food.add("Groceries");
        food.add("Restaurant");

        List<Drawable> foodimg = new ArrayList<>();
        foodimg.add(ContextCompat.getDrawable(this,R.drawable.canteen));
        foodimg.add(ContextCompat.getDrawable(this,R.drawable.groceries));
        foodimg.add(ContextCompat.getDrawable(this,R.drawable.restaurant));

        List<CardView> foodbtns = new ArrayList<>();
        foodbtns.add( new CardView(this));
        foodbtns.add(new CardView(this));
        foodbtns.add(new CardView(this));

        List<EditText> foodet =  new ArrayList<>();
        foodet.add(new EditText(this));
        foodet.add(new EditText(this));
        foodet.add(new EditText(this));

        List<String> health = new ArrayList<>();
        health.add("Barber");
        health.add("Body Care");
        health.add("Doctor Visit");
        health.add("Life Insurance");
        health.add("Pharmacy");

        List<Drawable> healthimg = new ArrayList<>();
        healthimg.add(ContextCompat.getDrawable(this, R.drawable.barber));
        healthimg.add(ContextCompat.getDrawable(this,R.drawable.bodycare));
        healthimg.add(ContextCompat.getDrawable(this,R.drawable.doctor));
        healthimg.add(ContextCompat.getDrawable(this,R.drawable.life_insurance));
        healthimg.add(ContextCompat.getDrawable(this,R.drawable.pharmacy));

        List<CardView> healthbtns = new ArrayList<>();
        healthbtns.add(new CardView(this));
        healthbtns.add(new CardView(this));
        healthbtns.add(new CardView(this));
        healthbtns.add(new CardView(this));
        healthbtns.add(new CardView(this));

        List<EditText> healthet = new ArrayList<>();
        healthet.add(new EditText(this));
        healthet.add(new EditText(this));
        healthet.add(new EditText(this));
        healthet.add(new EditText(this));
        healthet.add(new EditText(this));

        List<String> housing = new ArrayList<>();
        housing.add("Cleaning");
        housing.add("Furniture");
        housing.add("Garden");
        housing.add("Insurance");
        housing.add("Rent");
        housing.add("Repairs");
        housing.add("Taxes");
        housing.add("Utilities");

        List<Drawable> housingimg = new ArrayList<>();
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.cleaning));
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.furniture));
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.garden));
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.house_insurance));
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.rent));
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.repairs));
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.taxes));
        housingimg.add(ContextCompat.getDrawable(this, R.drawable.utilities));

        List<CardView> housingbtns = new ArrayList<>();
        housingbtns.add(new CardView(this));
        housingbtns.add(new CardView(this));
        housingbtns.add(new CardView(this));
        housingbtns.add(new CardView(this));
        housingbtns.add(new CardView(this));
        housingbtns.add(new CardView(this));
        housingbtns.add(new CardView(this));
        housingbtns.add(new CardView(this));

        List<EditText> housinget = new ArrayList<>();
        housinget.add(new EditText(this));
        housinget.add(new EditText(this));
        housinget.add(new EditText(this));
        housinget.add(new EditText(this));
        housinget.add(new EditText(this));
        housinget.add(new EditText(this));
        housinget.add(new EditText(this));
        housinget.add(new EditText(this));


        List<String> transport = new ArrayList<>();
        transport.add("Automobile Insurance");
        transport.add("Automobile Fees");
        transport.add("Fuel");
        transport.add("Parking");
        transport.add("Tolls");
        transport.add("Transportation");

        List<Drawable> transportimg = new ArrayList<>();
        transportimg.add(ContextCompat.getDrawable(this,R.drawable.car_insurance));
        transportimg.add(ContextCompat.getDrawable(this,R.drawable.car_fees));
        transportimg.add(ContextCompat.getDrawable(this,R.drawable.fuel));
        transportimg.add(ContextCompat.getDrawable(this,R.drawable.parking));
        transportimg.add(ContextCompat.getDrawable(this,R.drawable.tolls));
        transportimg.add(ContextCompat.getDrawable(this,R.drawable.transportation));

        List<CardView> transportbtns = new ArrayList<>();
        transportbtns.add(new CardView(this));
        transportbtns.add(new CardView(this));
        transportbtns.add(new CardView(this));
        transportbtns.add(new CardView(this));
        transportbtns.add(new CardView(this));
        transportbtns.add(new CardView(this));

        List<EditText> transportet = new ArrayList<>();
        transportet.add(new EditText(this));
        transportet.add(new EditText(this));
        transportet.add(new EditText(this));
        transportet.add(new EditText(this));
        transportet.add(new EditText(this));
        transportet.add(new EditText(this));


        mList.add(new DataModel(clothing, clothingimg, clothingbtns,clothinget,"Clothing", ContextCompat.getDrawable(this,R.drawable.clothing)));
        mList.add(new DataModel(communication, communicationimg, communicationbtns,communicationet,"Communication", ContextCompat.getDrawable(this,R.drawable.phone)));
        mList.add(new DataModel(entertainment, entertainmentimg, entertainmentbtns,entertainmentet,"Entertainment", ContextCompat.getDrawable(this,R.drawable.entertainment)));
        mList.add(new DataModel(finance, financeimg, financebtns,financeet,"Finance",ContextCompat.getDrawable(this,R.drawable.finance)));
        mList.add(new DataModel(food, foodimg, foodbtns,foodet,"Food",ContextCompat.getDrawable(this,R.drawable.food)));
        mList.add(new DataModel(health, healthimg, healthbtns,healthet,"Health", ContextCompat.getDrawable(this,R.drawable.health)));
        mList.add(new DataModel(housing, housingimg, housingbtns,housinget,"Housing", ContextCompat.getDrawable(this,R.drawable.house)));
        mList.add(new DataModel(transport, transportimg, transportbtns,transportet,"Transport", ContextCompat.getDrawable(this,R.drawable.transport)));

        adapter = new ItemAdapter(mList);
        recyclerView.setAdapter(adapter);


    }
    private void toIncome(){
        continue_home = findViewById(R.id.continue_to_incomes_btn);
        continue_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Define_categories.this, Define_incomes.class));
            }
        });
    }

    /*@Override
    public void onBackPressed() {
        finishAffinity();
    }*/
}
        /*SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE );

        String firstTime = sharedPreferences.getString("First Time Install","");

        if(firstTime.equals("Yes")){
            Intent intent = new Intent(Define_categories.this, Define_categories.class);
            startActivity(intent);
        }
        else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("First Time Install", "Yes");
            editor.apply();
        }*/
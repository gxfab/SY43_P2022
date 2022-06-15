package com.example.sy43;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.sy43.database.AppDatabase;
import com.example.sy43.database.Category;
import com.example.sy43.database.SubCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class add_category extends DialogFragment {
    private RecyclerView recyclerView;
    private List<DataModel> mList;
    private ItemAdapter adapter;
    List<SubCategory> subCategory;
    List<String> subcategories_titles  ;
    List<String> subcategories_enveloppes;
    double sum =0;
    int i=0;

    AppDatabase db = AppDatabase.getInstance(getContext());
    List<SubCategory> subcategories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);

        recyclerView = view.findViewById(R.id.add_sub_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mList = new ArrayList<>();

        List<String> clothing = new ArrayList<>();
        addToList("Accessories",clothing);
        addToList("Clothes",clothing);
        addToList("Footwear",clothing);


        List<Drawable> clothingimg = new ArrayList<>();
        for (int i =0 ; i<clothing.size();i++){
            clothingimg.add(ContextCompat.getDrawable(getContext(),R.drawable.accessories));
        }

        List<CardView> clothingbtns = new ArrayList<>();
        for (int i =0 ; i<clothing.size();i++){
            clothingbtns.add(new CardView(getContext()));
        }
        List<EditText> clothinget = new ArrayList<>();
        for (int i =0 ; i<clothing.size();i++){
            clothinget.add(new EditText(getContext()));
        }


        List<String> communication = new ArrayList<>();
        addToList("Internet",communication);
        addToList("Phone",communication);
        addToList("Television",communication);


        List<Drawable> communicationimg= new ArrayList<>();
        for (int i =0 ; i<communication.size();i++){
            communicationimg.add(ContextCompat.getDrawable(getContext(), R.drawable.internet));

        }
        List<CardView> communicationbtns = new ArrayList<>();
        for (int i =0 ; i<communication.size();i++){
            communicationbtns.add(new CardView(getContext()));
        }
        List<EditText> communicationet = new ArrayList<>();
        for (int i =0 ; i<communication.size();i++){
            communicationet.add(new EditText(getContext()));
        }


        List<String> entertainment = new ArrayList<>();
        addToList("Books",entertainment);
        addToList("Events",entertainment);
        addToList("Gaming",entertainment);
        addToList("Gift",entertainment);
        addToList("Hangouts",entertainment);
        addToList("Hotel",entertainment);
        addToList("Multimedia",entertainment);
        addToList("Pet",entertainment);
        addToList("Sports",entertainment);
        addToList("Vacation",entertainment);

        List<Drawable> entertainmentimg = new ArrayList<>();
        for(int i=0; i<entertainment.size();i++){
            entertainmentimg.add(ContextCompat.getDrawable(getContext(),R.drawable.books));
        }
        List<CardView> entertainmentbtns = new ArrayList<>();
        for(int i=0; i<entertainment.size();i++){
            entertainmentbtns.add( new CardView(getContext()));
        }

        List<EditText> entertainmentet = new ArrayList<>();
        for(int i=0; i<entertainment.size();i++){
            entertainmentet.add(new EditText(getContext()));
        }


        List<String> finance = new ArrayList<>();
        addToList("Banking Fees",finance);
        addToList("Loan",finance);
        addToList("Salary tax",finance);


        List<Drawable> financeimg = new ArrayList<>();
        for(int i=0; i<finance.size();i++){
            financeimg.add(ContextCompat.getDrawable(getContext(),R.drawable.banking));
        }
        List<CardView> financebtns = new ArrayList<>();
        for(int i=0; i<finance.size();i++){
            financebtns.add(new CardView(getContext()));
        }

        List<EditText> financeet = new ArrayList<>();
        for(int i=0; i<finance.size();i++){
            financeet.add(new EditText(getContext()));
        }


        List<String> food = new ArrayList<>();
        addToList("Canteen",food);
        addToList("Groceries",food);
        addToList("Restaurant",food);


        List<Drawable> foodimg = new ArrayList<>();
        for(int i=0; i<food.size();i++){
            foodimg.add(ContextCompat.getDrawable(getContext(),R.drawable.canteen));
        }

        List<CardView> foodbtns = new ArrayList<>();
        for(int i=0; i<food.size();i++){
            foodbtns.add( new CardView(getContext()));
        }


        List<EditText> foodet =  new ArrayList<>();
        for(int i=0; i<food.size();i++){
            foodet.add( new EditText(getContext()));
        }


        List<String> health = new ArrayList<>();
        addToList("Barber",health);
        addToList("Body Care",health);
        addToList("Doctor Visit",health);
        addToList("Life Insurance",health);
        addToList("Pharmacy",health);

        List<Drawable> healthimg = new ArrayList<>();
        for(int i=0;i<health.size();i++){
            healthimg.add(ContextCompat.getDrawable(getContext(), R.drawable.barber));
        }
        List<CardView> healthbtns = new ArrayList<>();
        for(int i=0;i<health.size();i++){
            healthbtns.add(new CardView(getContext()));
        }

        List<EditText> healthet = new ArrayList<>();
        for(int i=0;i<health.size();i++){
            healthet.add(new EditText(getContext()));
        }


        List<String> housing = new ArrayList<>();
        addToList("Cleaning",health);
        addToList("Furniture",health);
        addToList("Garden",health);
        addToList("Insurance",health);
        addToList("Rent",health);
        addToList("Repairs",health);
        addToList("Taxes",health);
        addToList("Utilities",health);

        List<Drawable> housingimg = new ArrayList<>();
        for(int i=0; i<housing.size();i++){
            housingimg.add(ContextCompat.getDrawable(getContext(), R.drawable.cleaning));
        }
        List<CardView> housingbtns = new ArrayList<>();
        for(int i=0; i<housing.size();i++) {
            housingbtns.add(new CardView(getContext()));
        }

        List<EditText> housinget = new ArrayList<>();
        for(int i=0; i<housing.size();i++) {
            housinget.add(new EditText(getContext()));
        }


        List<String> transport = new ArrayList<>();
        addToList("Automobile Insurance",health);
        addToList("Automobile Fees",health);
        addToList("Fuel",health);
        addToList("Parking",health);
        addToList("Tolls",health);
        addToList("Transportation",health);

        List<Drawable> transportimg = new ArrayList<>();
        for(int i=0; i<transport.size();i++){
            transportimg.add(ContextCompat.getDrawable(getContext(),R.drawable.car_insurance));
        }

        List<CardView> transportbtns = new ArrayList<>();
        for(int i=0; i<transport.size();i++) {
            transportbtns.add(new CardView(getContext()));
        }

        List<EditText> transportet = new ArrayList<>();
        for(int i=0; i<transport.size();i++) {
            transportet.add(new EditText(getContext()));
        }

        mList.add(new DataModel(clothing, clothingimg, clothingbtns,clothinget,"Clothing", ContextCompat.getDrawable(getContext(),R.drawable.clothing)));
        mList.add(new DataModel(communication, communicationimg, communicationbtns,communicationet,"Communication", ContextCompat.getDrawable(getContext(),R.drawable.phone)));
        mList.add(new DataModel(entertainment, entertainmentimg, entertainmentbtns,entertainmentet,"Entertainment", ContextCompat.getDrawable(getContext(),R.drawable.entertainment)));
        mList.add(new DataModel(finance, financeimg, financebtns,financeet,"Finance",ContextCompat.getDrawable(getContext(),R.drawable.finance)));
        mList.add(new DataModel(food, foodimg, foodbtns,foodet,"Food",ContextCompat.getDrawable(getContext(),R.drawable.food)));
        mList.add(new DataModel(health, healthimg, healthbtns,healthet,"Health", ContextCompat.getDrawable(getContext(),R.drawable.health)));
        mList.add(new DataModel(housing, housingimg, housingbtns,housinget,"Housing", ContextCompat.getDrawable(getContext(),R.drawable.house)));
        mList.add(new DataModel(transport, transportimg, transportbtns,transportet,"Transport", ContextCompat.getDrawable(getContext(),R.drawable.transport)));

        adapter = new ItemAdapter(mList);
        recyclerView.setAdapter(adapter);


        return view;
    }


    private void addToList(String title, List<String> list){
        subcategories = db.subCategoryDao().getAll();
        List<String > titles = new ArrayList<>();
        for (int i=0; i<subcategories.size();i++){
            titles.add(subcategories.get(i).name) ;
        }
        if(!titles.contains(title)){
            list.add(title);
        }
    }
}
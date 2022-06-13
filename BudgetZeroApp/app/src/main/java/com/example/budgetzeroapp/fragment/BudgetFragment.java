package com.example.budgetzeroapp.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.budgetzeroapp.BudgetRecyclerViewAdapter;
import com.example.budgetzeroapp.R;

import java.util.ArrayList;

public class BudgetFragment extends Fragment implements BudgetRecyclerViewAdapter.ItemClickListener {
    private BudgetRecyclerViewAdapter adapter;

    public BudgetFragment() {
        // Required empty public constructor
    }

    public static BudgetFragment newInstance(String param1, String param2) {
        BudgetFragment fragment = new BudgetFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_budget, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**Category click listeners**/
        view.findViewById(R.id.category_shopping).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog shoppingDialogue = new Dialog(view.getContext(), android.R.style.Theme_Black_NoTitleBar);
                shoppingDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                shoppingDialogue.setContentView(R.layout.dialogue);
                shoppingDialogue.setCancelable(true);
                shoppingDialogue.show();
            }
        });
        view.findViewById(R.id.category_vehicle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog vehicleDialogue = new Dialog(view.getContext(), android.R.style.Theme_Black_NoTitleBar);
                vehicleDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                vehicleDialogue.setContentView(R.layout.dialogue);
                vehicleDialogue.setCancelable(true);
                vehicleDialogue.show();
            }
        });
        view.findViewById(R.id.category_leisure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog leisureDialogue = new Dialog(view.getContext(), android.R.style.Theme_Black_NoTitleBar);
                leisureDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                leisureDialogue.setContentView(R.layout.dialogue);
                leisureDialogue.setCancelable(true);
                leisureDialogue.show();
            }
        });
        view.findViewById(R.id.category_health).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog healthDialogue = new Dialog(view.getContext(), android.R.style.Theme_Black_NoTitleBar);
                healthDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                healthDialogue.setContentView(R.layout.dialogue);
                healthDialogue.setCancelable(true);
                healthDialogue.show();
            }
        });
        view.findViewById(R.id.category_miscellaneous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog miscellaneousDialogue = new Dialog(view.getContext(), android.R.style.Theme_Black_NoTitleBar);
                miscellaneousDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                miscellaneousDialogue.setContentView(R.layout.dialogue);
                miscellaneousDialogue.setCancelable(true);
                miscellaneousDialogue.show();
            }
        });

        /**Sorting RecyclerView Initialization**/
        // data to populate the RecyclerView with
        ArrayList<String> sortingItems = new ArrayList<>();
        sortingItems.add("Category");
        sortingItems.add("Spent");
        sortingItems.add("Budget");

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.budget_sorting);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);

        adapter = new BudgetRecyclerViewAdapter(view.getContext(), sortingItems);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        // USE THIS LISTENER TO DETERMINE WHICH SORTING TO APPLY
        Toast.makeText(view.getContext(), "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }
}
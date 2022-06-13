package com.example.budgetzeroapp.fragment.cashflow;

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

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.adapter.BudgetRecyclerViewAdapter;

import java.util.ArrayList;

public class CashFlowGeneralFragment extends Fragment implements BudgetRecyclerViewAdapter.ItemClickListener{
    private BudgetRecyclerViewAdapter adapter;

    public CashFlowGeneralFragment() {
        // Required empty public constructor
    }

    public static CashFlowGeneralFragment newInstance(String param1, String param2) {
        CashFlowGeneralFragment fragment = new CashFlowGeneralFragment();
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
        return inflater.inflate(R.layout.fragment_cash_flow_general, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
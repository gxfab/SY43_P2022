package com.example.budgetzeroapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.ClickableListManager;
import com.example.budgetzeroapp.tool.adapter.BudgetAdapter;
import com.example.budgetzeroapp.tool.adapter.BudgetRecyclerViewAdapter;
import com.example.budgetzeroapp.tool.adapter.SavingsAdapter;
import com.example.budgetzeroapp.tool.item.CategoryItem;
import com.example.budgetzeroapp.tool.item.SavingsItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SavingsDebtTabFragment extends DataBaseFragment implements BudgetRecyclerViewAdapter.ItemClickListener {
    private BudgetRecyclerViewAdapter adapter;
    private ListView categories;
    RecyclerView recyclerView;
    List<SavingsItem> items;

    public SavingsDebtTabFragment() {
        super();
    }

    public static SavingsTabFragment newInstance(String param1, String param2) {
        return new SavingsTabFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_savings_debts_tab, container, false);
        categories = view.findViewById(R.id.list_view_cat);
        recyclerView = view.findViewById(R.id.budget_sorting);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**Listview**/
        categories = view.findViewById(R.id.list_view_cat);
        items = SavingsItem.initSavingsList(false);
        ClickableListManager.clickableSavingsList(categories, items);
        SavingsAdapter savingsAdapter = new SavingsAdapter(items);
        categories.setAdapter(savingsAdapter);


        /**Sorting**/
        ArrayList<String> sortingItems = new ArrayList<>();
        sortingItems.add("Name");
        sortingItems.add("%Paid");
        sortingItems.add("Amount");

        // set up the RecyclerView
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);

        adapter = new BudgetRecyclerViewAdapter(view.getContext(), sortingItems);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch(position){
            case 0 : Collections.sort(items, (savingsItem, t1) -> savingsItem.getName().compareTo(t1.getName()));
                break;
            case 1 : Collections.sort(items, (savingsItem, t1) -> (savingsItem.getProgress() - t1.getProgress()));
                break;
            case 2 : Collections.sort(items, (savingsItem, t1) -> (int) (savingsItem.getObjective()-t1.getObjective()));
                break;
        }
        categories.setAdapter(new SavingsAdapter(items));
    }
}
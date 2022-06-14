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

import com.example.budgetzeroapp.tool.ClickableListManager;
import com.example.budgetzeroapp.tool.adapter.BudgetRecyclerViewAdapter;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.item.CategoryItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BudgetFragment extends DataBaseFragment implements BudgetRecyclerViewAdapter.ItemClickListener {

    private BudgetRecyclerViewAdapter adapter;
    private ListView list;
    private List<CategoryItem> items;

    public BudgetFragment() {
        // Required empty public constructor
    }

    public static BudgetFragment newInstance(String param1, String param2) {
        return new BudgetFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_budget, container, false);
        list = view.findViewById(R.id.list_view_cat);
        items = CategoryItem.initCategoryList(database, false);
        list = ClickableListManager.clickableBudgetList(list, items);
        return view;
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

        switch(position){
            case 1 : Collections.sort(items, (categoryItem, t1) -> categoryItem.getId() - t1.getId());
                break;
            case 2 : Collections.sort(items, (categoryItem, t1) -> (int) (t1.getTotal() - categoryItem.getTotal()));
                break;
            case 3 : Collections.sort(items, (categoryItem, t1) -> (int) (categoryItem.getBudget()-t1.getBudget()));
                break;
        }
    }
}
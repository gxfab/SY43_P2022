package com.example.budgetzeroapp.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.tool.ClickableListManager;
import com.example.budgetzeroapp.tool.ToolBar;
import com.example.budgetzeroapp.tool.adapter.BudgetAdapter;
import com.example.budgetzeroapp.tool.adapter.BudgetRecyclerViewAdapter;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.adapter.ProgressBarAdapter;
import com.example.budgetzeroapp.tool.item.CategoryItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BudgetFragment extends DataBaseFragment implements BudgetRecyclerViewAdapter.ItemClickListener {
    private BudgetRecyclerViewAdapter adapter;
    private ListView listView;
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
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**Listview**/
        listView = view.findViewById(R.id.list_view_cat);
        items = CategoryItem.initCategoryList(database.getMainExpCat(), false);
        listView = ClickableListManager.clickableBudgetList(listView, items);

        BudgetAdapter budgetAdapter = new BudgetAdapter(items);
        listView.setAdapter(budgetAdapter);

        listView.setVerticalScrollBarEnabled(false);

        /**Sorting RecyclerView Initialization**/
        // data to populate the RecyclerView with
        ArrayList<String> sortingItems = new ArrayList<>();
        sortingItems.add("Name");
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

        ToolBar.getInstance().initToolBar(view, R.id.toolbar_budget);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch(position){
            case 0 : Collections.sort(items, (categoryItem, t1) -> categoryItem.getName().compareTo(t1.getName()));
                break;
            case 1 : Collections.sort(items, (categoryItem, t1) -> (int) (categoryItem.getTotal() - t1.getTotal()));
                break;
            case 2 : Collections.sort(items, (categoryItem, t1) -> (int) (t1.getBudget()-categoryItem.getBudget()));
                break;
        }
        listView.setAdapter(new BudgetAdapter(items));
    }

    public static void redirectToViewExpenseCat(int cat_id)
    {
        NavController navController= Navigation.findNavController(MainActivity.getActivity(), R.id.nav_host_fragment);
        NavDirections action = BudgetFragmentDirections.navigateToViewExpenseCatFromBudget(cat_id);
        navController.navigate(action);
    }
}

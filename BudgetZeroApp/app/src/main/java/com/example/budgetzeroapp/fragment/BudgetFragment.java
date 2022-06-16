package com.example.budgetzeroapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
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
        items = CategoryItem.initCategoryList(database, false);
        listView = ClickableListManager.clickableBudgetList(listView, items);

        BudgetAdapter budgetAdapter = new BudgetAdapter(items);
        listView.setAdapter(budgetAdapter);

        listView.setVerticalScrollBarEnabled(false);

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

        ToolBar.getInstance().initToolBar(view, R.id.toolbar_budget);


        /**Navigation
         Toolbar toolbar = view.findViewById(R.id.toolbar_budget);
         NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.budgetFragment,R.id.addCategoryFragment).build();
        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                NavController navController = Navigation.findNavController(view);
                switch(item.getItemId()){
                    case R.id.addCategory:
                        navController.navigate(R.id.navigate_to_addCategory);
                        break;
                    case R.id.next_day:
                        //change day
                        break;
                    case R.id.mode:
                        //change mode (auto/manual)
                        break;
                }
                return true;
            }
        });**/


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

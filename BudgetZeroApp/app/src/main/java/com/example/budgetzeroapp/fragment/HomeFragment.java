package com.example.budgetzeroapp.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.ClickableListManager;
import com.example.budgetzeroapp.tool.ToolBar;
import com.example.budgetzeroapp.tool.adapter.ProgressBarAdapter;
import com.example.budgetzeroapp.tool.item.CategoryItem;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;


public class HomeFragment extends DataBaseFragment {
    private PieChart pieChart;
    private ListView listView;
    private ProgressBarAdapter adapter;

    public HomeFragment() {
        super();
    }


    public static HomeFragment newInstance(String param1, String param2) {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        listView = view.findViewById(R.id.list_view_cat);
        pieChart = view.findViewById(R.id.pie_chart);

        /**Setup listview**/
        super.onViewCreated(view, savedInstanceState);
        ArrayList<CategoryItem> items = CategoryItem.initCategoryList(database.getMainExpCat(), true);
        ClickableListManager.clickableProgressBarList(listView, items);

        listView.setVerticalScrollBarEnabled(false);

        /**Setup piechart**/
        setupPieChart();
        loadPieChartData(items);

        ToolBar.getInstance().initToolBar(view, R.id.toolbar_home);

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        Toolbar toolbar = view.findViewById(R.id.toolbar_home);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment,R.id.viewExpenseCatFragment).build();
        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);

        /*toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                NavController navController = Navigation.findNavController(view);
                switch(item.getItemId()){
                    case R.id.expense:
                        navController.navigate(R.id.action_homeFragment_to_addCategoryFragment);
                        break;
                }
                return true;
            }
        });*/

        /*view.findViewById(R.id.text_click).setOnClickListener(new View.OnClickListener() {
            NavController navController = Navigation.findNavController(view);
            @Override
            public void onClick(View view) {
               // NavDirections action = HomeFragmentDirections.navigateToViewExpenseCatFromHome();
                navController.navigate(R.id.navigate_to_viewExpenseCat_from_home);
            }
        });*/

        /**Navigation
        Toolbar toolbar = view.findViewById(R.id.toolbar_home);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                NavController navController = Navigation.findNavController(view);
                switch(item.getItemId()){
                    case R.id.next_day:
                        //change day
                        break;
                }
                return true;
            }
        });**/

    }


    private void setupPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setEnabled(false);
    }

    private void loadPieChartData(List<CategoryItem> list) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        for(CategoryItem i : list)
            if (i.getPercent() != 0.0)
                entries.add(new PieEntry(i.getPercent()/100, i.getName()));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS) colors.add(color);

        for (int color: ColorTemplate.VORDIPLOM_COLORS) colors.add(color);


        PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}
package com.example.budgetzeroapp.fragment;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.ClickableListManager;
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
        ArrayList<CategoryItem> items = CategoryItem.initCategoryList(database, true);
        listView = ClickableListManager.clickableProgressBarList(listView, items);
        adapter = new ProgressBarAdapter(items);
        listView.setAdapter(adapter);

        listView.setVerticalScrollBarEnabled(false);

        /**Setup piechart**/
        setupPieChart();
        loadPieChartData(items);

        /**Navigation**/

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
package com.example.budgetzeroapp.fragment;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.list.ClickableListManager;
import com.example.budgetzeroapp.tool.list.item.ProgressBarItem;

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
    private ListView list;

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
        list = view.findViewById(R.id.list_view_cat);
        pieChart = view.findViewById(R.id.pie_chart);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<ProgressBarItem> items = initPBList();
        list = ClickableListManager.clickableListPB(list, items);
        setupPieChart();
        loadPieChartData(items);

    }

    public List<ProgressBarItem> initPBList(){

        Cursor rows = database.getMainExpCat();
        List<ProgressBarItem> list = new ArrayList<>();
        int id, percent;
        float amount, total;
        total = database.getSumExp();
        String name;
        rows.moveToFirst();
            while (!rows.isAfterLast()) {
                id = rows.getInt(rows.getColumnIndexOrThrow("id"));
                name = rows.getString(rows.getColumnIndexOrThrow("name"));
                amount = database.getSumCatExp(id);
                percent = (int) (100 * amount / total);
                list.add(new ProgressBarItem(id, name, amount, percent));
                rows.moveToNext();
            }
        amount = database.getSumSav();
        list.add(new ProgressBarItem(-1, "Savings", amount, (int)(100*amount/total)));
        amount = database.getSumDebt();
        list.add(new ProgressBarItem(0, "Debts", amount, (int)(100*amount/total)));
        return list;
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

    private void loadPieChartData(List<ProgressBarItem> list) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        for(ProgressBarItem i : list)
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
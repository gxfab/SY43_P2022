package com.example.sy43;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sy43.database.AppDatabase;
import com.example.sy43.database.Category;
import com.example.sy43.database.Expenses;
import com.example.sy43.database.Income;
import com.example.sy43.database.MonthlyRevenue;
import com.example.sy43.database.SubCategory;
import com.google.android.material.tabs.TabLayout;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Home extends AppCompatActivity {

    private TabLayout tabLayout;
    private TextView total_income, total_expense, total_balance;
    private ViewPager2 viewPager2;
    private MyFragmentAdapter adapter;
    private Define_incomes define_incomes;
    private Spinner monthSpinner, yearSpinner;

    PieChart pieChart;

    //TODO lezm e3mel nafes l chi michen popups l subcategories
    // w zabit l subcategories ma3 l icons,
    // w zabit l switch ben categories w home,
    // w chakel l home mech zabit l scroll w l tabs

    //Lezm chouf lech l spinners ma 3am ye2ro l data.. check l arraylist adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        total_income = findViewById(R.id.total_income_amount);

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String amount = extras.getString("total_income_value");
            total_income.setText(amount);
        } else {
            double amount = 0.0;
            for(Income inc : db.incomeDao().getAll()){
                amount += inc.value;
            }
            total_income.setText(String.valueOf(amount)+"$");
        }

        toCategories();
        toOverview();

        addToPieChart(40,25,15,20);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2= findViewById(R.id.child_fragment_container);

        tabLayout.addTab(tabLayout.newTab().setText("Latest Activity"));
        tabLayout.addTab(tabLayout.newTab().setText("Add new expense"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter= new MyFragmentAdapter(fragmentManager,getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


        total_expense = findViewById(R.id.expense_amount);
        total_balance = findViewById(R.id.balance_amount);
        monthSpinner = findViewById(R.id.spinner);
        yearSpinner = findViewById(R.id.spinner4);

        String[] monthsArray = getResources().getStringArray(R.array.Months_Array);

        List<String> months = new ArrayList<String>();
        for (MonthlyRevenue mth: db.monthlyRevenueDao().getAll()) {
            months.add(Month.of(mth.month).getDisplayName(TextStyle.FULL, Locale.ENGLISH));
            //months.add(mth);
        }
        ArrayAdapter<String> monthData = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, months);
        monthData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthData);


        List<String> years = new ArrayList<String>();
        for (MonthlyRevenue mth: db.monthlyRevenueDao().getAll()) {
            years.add(String.valueOf(mth.year));
            //months.add(mth);
        }
        ArrayAdapter<String> yearData = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, years);
        yearData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearData);

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                 @Override
                                                 public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {updateExpenseAndBalance(monthsArray, db);}
                                                 @Override
                                                 public void onNothingSelected(AdapterView<?> adapterView) {}});

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {updateExpenseAndBalance(monthsArray, db);}
                                                @Override
                                                public void onNothingSelected(AdapterView<?> adapterView) {}});

//        monthSpinner.setSelection(months.indexOf(Month.of(Calendar.getInstance().get(Calendar.MONTH)).getDisplayName(TextStyle.FULL, Locale.ENGLISH)));
//
          updateExpenseAndBalance(monthsArray, db);
//        double exp_total=0.0;
//        int selectedMonth = Arrays.asList(monthsArray).indexOf(monthSpinner.getSelectedItem().toString())+1;
//        int selectedYear = Integer.parseInt(yearSpinner.getSelectedItem().toString());
//        List<Expenses> monthlyExpenses = db.expensesDao().findByMonth(db.monthlyRevenueDao().findByMonthAndYear(selectedMonth,selectedYear).id);
//        for (Expenses exp : monthlyExpenses) {
//            exp_total+=exp.value;
//        }
//        total_expense.setText(String.valueOf(exp_total)+"$");
//        String incomeText = total_income.getText().toString();
//        total_balance.setText(Double.parseDouble(incomeText.substring(0,incomeText.length()-1))-exp_total+"$");

        //List<Expenses> expensesList = db.expensesDao().findByMonth(monthSpinner.getSelectedItem().toString())
    }

    private void addToPieChart(int i1, int i2, int i3, int i4){
        PieChart chart = findViewById(R.id.piechart);
        chart.addPieSlice(new PieModel("Housing",i1, Color.parseColor("#FFA726")));
        chart.addPieSlice(new PieModel("Transportation",i2, Color.parseColor("#66BB6A")));
        chart.addPieSlice(new PieModel("Finance",i3, Color.parseColor("#EF5350")));
        chart.addPieSlice(new PieModel("Communication",i4, Color.parseColor("#2986F6")));

        chart.startAnimation();
        chart.setClickable(false);
    }

    private void toCategories(){
        ImageView categories_icon = findViewById(R.id.categories_icon);
        categories_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Categories.class));
            }
        });
    }

    private void toOverview(){
        ImageView overview = findViewById(R.id.overview_icon);
        overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Overview.class));
            }
        });
    }

    private void updateExpenseAndBalance(String[] monthsArray, AppDatabase db){
        double exp_total=0.0;
        int selectedMonth = Arrays.asList(monthsArray).indexOf(monthSpinner.getSelectedItem().toString())+1;
        int selectedYear = Integer.parseInt(yearSpinner.getSelectedItem().toString());
        List<Expenses> monthlyExpenses = db.expensesDao().findByMonth(db.monthlyRevenueDao().findByMonthAndYear(selectedMonth,selectedYear).id);
        for (Expenses exp : monthlyExpenses) {
            exp_total+=exp.value;
        }
        total_expense.setText(String.valueOf(exp_total)+"$");
        String incomeText = total_income.getText().toString();
        total_balance.setText(Double.parseDouble(incomeText.substring(0,incomeText.length()-1))-exp_total+"$");
    }
}
package com.example.sy43;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.example.sy43.database.Projects;
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
    private Spinner monthSpinner, yearSpinner;
    private RecyclerView recyclerView;
    private List<String> l1,l2,l3,l4;
    private ProjectsDisplayAdapter projectsDisplayAdapter;
    Double allocation = 0.0;


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
        }else {
            double amount = 0.0;
            for(Income inc : db.incomeDao().getAll()){
                amount += inc.value;
            }
            total_income.setText(String.valueOf(amount)+"$");
        }

        toCategories();
        toOverview();

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
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateExpenseAndBalance(monthsArray, db);
                Fragment frag = fragmentManager.findFragmentByTag("f"+viewPager2.getCurrentItem());
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.detach(frag);
                ft.commitNow();
                ft = fragmentManager.beginTransaction();
                ft.attach(frag);
                ft.commitNow();
            }
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
        String currentMonth = monthsArray[Calendar.getInstance().get(Calendar.MONTH)];
        int monthPosition = months.indexOf(currentMonth);
        monthSpinner.setSelection(monthPosition);
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


        recyclerView = findViewById(R.id.display_projects_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        l1 = new ArrayList<>();
        l2 = new ArrayList<>();
        l3 = new ArrayList<>();
        l4 = new ArrayList<>();

        List<Projects> projects = db.projectDao().getAll();
        for(int i=0; i<projects.size();i++){

            allocation = 0.0;
            String allocationstr = total_balance.getText().toString();
            allocation+= (Double.parseDouble(allocationstr.substring(0,allocationstr.length()-1))) * (projects.get(i).percentage/100);
            l1.add(projects.get(i).name);
            l2.add(Double.toString(projects.get(i).value) + "$");
            l3.add(Integer.toString((int)projects.get(i).percentage/10) + "/10");
            l4.add("Allocated : " + Double.toString(allocation) + "$");
        }

        projectsDisplayAdapter = new ProjectsDisplayAdapter(l1,l2,l3,l4);
        recyclerView.setAdapter(projectsDisplayAdapter);

        //List<Expenses> expensesList = db.expensesDao().findByMonth(monthSpinner.getSelectedItem().toString())
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
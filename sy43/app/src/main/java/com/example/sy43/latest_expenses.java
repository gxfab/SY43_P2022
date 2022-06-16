package com.example.sy43;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sy43.database.AppDatabase;
import com.example.sy43.database.Expenses;
import com.example.sy43.database.SubCategory;

import java.security.PrivateKey;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class latest_expenses extends Fragment {


    private AddExpenseAdapter adapter;
    private RecyclerView recyclerView;
    private TextView t1,t2,t3, t4;
    List<String> l1,l2,l3,l4;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_expenses, container, false);

        t1 = view.findViewById(R.id.latest_expense_name);
        t2 = view.findViewById(R.id.latest_expense_value);
        t3 = view.findViewById(R.id.latest_expense_date);
        t3 = view.findViewById(R.id.latest_expense_subcategory);
        recyclerView = view.findViewById(R.id.latest_expense_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        AppDatabase db = AppDatabase.getInstance(getContext());

        AddExpenseAdapter adapter = new AddExpenseAdapter(l1,l2,l3,l4);
        recyclerView.setAdapter(adapter);
        String[] monthsArray = getResources().getStringArray(R.array.Months_Array);
        Spinner monthSpinner = getActivity().findViewById(R.id.spinner); int selectedMonth = Arrays.asList(monthsArray).indexOf(monthSpinner.getSelectedItem().toString())+1;
        Spinner yearSpinner = getActivity().findViewById(R.id.spinner4); int selectedYear = Integer.parseInt(yearSpinner.getSelectedItem().toString());
        int month = db.monthlyRevenueDao().findByMonthAndYear(selectedMonth,selectedYear).id;
        List<Expenses> expenses = db.expensesDao().findByMonth(month);
        l1 = new ArrayList<>();
        l2 = new ArrayList<>();
        l3 = new ArrayList<>();
        l4 = new ArrayList<>();
        for(Expenses exp : expenses){
            l1.add(exp.date);
            l2.add(exp.name);
            l3.add(Double.toString(exp.value) + "$");
            String subcategory = db.subCategoryDao().loadAllByIDs(new int[]{exp.subcategory}).get(0).name;
            l4.add("Subcategory : " + subcategory);
        }
        adapter = new AddExpenseAdapter(l1, l2, l3, l4);
        recyclerView.setAdapter(adapter);
        return view;
    }


    public void show_new_expense (RecyclerView recyclerView, CardView cardView, String date, String name, String amount){
        t1.setText(name);
        t2.setText(amount);
        t3.setText(date);

        cardView.addView(t1);
        cardView.addView(t2);
        cardView.addView(t3);
        recyclerView.addView(cardView);


    }
}
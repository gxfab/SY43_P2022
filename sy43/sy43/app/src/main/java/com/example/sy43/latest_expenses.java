package com.example.sy43;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;


public class latest_expenses extends Fragment {

    RecyclerView recyclerView;
    private AddExpenseAdapter adapter;
    private String date, name, value;
    public List<String> dates, expense_name, expense_amount;
    private add_expense add_expense;
    private EditText t1,t2;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_latest_expenses, container, false);
        /*recyclerView = view.findViewById(R.id.latest_expenses_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));

        dates = new ArrayList<>();
        expense_name = new ArrayList<>();
        expense_amount = new ArrayList<>();

        t1 = add_expense.et_name;
        t2 = add_expense.et_amount;
        t1 = view.findViewById(R.id.add_expense_name);
        name = t1.getText().toString();

        t2 = view.findViewById(R.id.add_expense_amount);
        value = t2.getText().toString();

        date = "06 June 2022";

        add_expense.addBtn = view.findViewById(R.id.add_expense_btn);
        add_expense.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expense_name.add(name);
                expense_amount.add(value);
                dates.add(date);

            }
        });

        adapter = new AddExpenseAdapter(dates, expense_name, expense_amount);
        recyclerView.setAdapter(adapter);
*/

        return view;
    }
}
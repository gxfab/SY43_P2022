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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;


public class latest_expenses extends Fragment {

    RecyclerView recyclerView;
    CardView cardView;
    private AddExpenseAdapter adapter;
    private String date, name, value;
    private add_expense add_expense;
    private TextView t1,t2,t3;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_expenses, container, false);

        Bundle bundle = getArguments();
        name = bundle.getString("Name");
        value = bundle.getString("Amount");
        date = bundle.getString("Date");

        t1 = view.findViewById(R.id.latest_expense_name);
        t2 = view.findViewById(R.id.latest_expense_value);
        t3 = view.findViewById(R.id.latest_expense_date);
        t1.setText(name);
        t2.setText(value);
        t3.setText(date);



        return view;
    }

    public void show_new_expense (RecyclerView recyclerView,CardView cardView, String date, String name, String amount){
        t1.setText(name);
        t2.setText(amount);
        t3.setText(date);

        cardView.addView(t1);
        cardView.addView(t2);
        cardView.addView(t3);
        recyclerView.addView(cardView);


    }
}
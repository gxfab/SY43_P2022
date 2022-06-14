package com.example.sy43;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class add_expense extends Fragment {
    protected EditText et_name, et_amount;
    private String date;
    protected Button addBtn;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);
        //recyclerView = view.findViewById(R.id.latest_expenses_rv);
        et_amount = view.findViewById(R.id.add_expense_amount);
        et_name = view.findViewById(R.id.add_expense_name);
        addBtn= view.findViewById(R.id.add_expense_btn);
        tabLayout = view.findViewById(R.id.tabLayout);

        date="1106JUNE";

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Name", et_name.getText().toString());
                bundle.putString("Amount", et_amount.getText().toString());
                bundle.putString("Date", date);

                latest_expenses latestExpenses = new latest_expenses();
                latestExpenses.setArguments(bundle);

                TabLayout.Tab tab = tabLayout.getTabAt(1);
                tab.select();
            }
        });


        return view;
    }
}
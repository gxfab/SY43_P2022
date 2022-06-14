package com.example.budgetzeroapp.fragment.cashflow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.tool.ClickableListManager;
import com.example.budgetzeroapp.tool.adapter.BudgetRecyclerViewAdapter;
import com.example.budgetzeroapp.tool.item.ExpenseItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CashFlowGeneralFragment extends DataBaseFragment{
    private BudgetRecyclerViewAdapter adapter;
    private List<ExpenseItem> items;
    private ListView list;

    public CashFlowGeneralFragment() { super(); }


    public static CashFlowGeneralFragment newInstance(String param1, String param2) {
        return  new CashFlowGeneralFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cash_flow_general, container, false);
        //list = view.findViewById(R.id.expense_list);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       /* items = ExpenseItem.allExpensesToList(database);
        list = ClickableListManager.clickableExpenseList(list, items);*/
    }
}
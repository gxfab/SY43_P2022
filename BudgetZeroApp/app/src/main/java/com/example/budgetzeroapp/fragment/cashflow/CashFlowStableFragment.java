package com.example.budgetzeroapp.fragment.cashflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.tool.ClickableListManager;
import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.tool.item.ExpenseItem;

import java.util.List;

public class CashFlowStableFragment extends DataBaseFragment {

    ListView earnList, expList;

    public CashFlowStableFragment() { super(); }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_cash_flow_stable, container, false);
        expList = view.findViewById(R.id.expenses_list);
        earnList = view.findViewById(R.id.earnings_list);
        List<ExpenseItem> earn = ExpenseItem.ExpensesToList(
                database.getLastMonthStable(DBHelper.TYPE_INC), database);
        List<ExpenseItem> exp = ExpenseItem.ExpensesToList(
                database.getLastMonthStable(DBHelper.TYPE_EXP), database);
        ClickableListManager.clickableExpenseList(expList, exp);
        ClickableListManager.clickableExpenseList(earnList, earn);
        return view;
    }
}
package com.example.budgetzeroapp.fragment.cashflow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.tool.ClickableListManager;
import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.tool.adapter.ExpenseAdapter;
import com.example.budgetzeroapp.tool.adapter.SavingsAdapter;
import com.example.budgetzeroapp.tool.item.ExpenseItem;
import com.example.budgetzeroapp.tool.item.SavingsItem;

import java.util.List;

public class CashFlowStableFragment extends DataBaseFragment {

    ListView earnList, expList;
    TextView expText, earnText;

    public CashFlowStableFragment() {
        super();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cash_flow_stable, container, false);
        expText = view.findViewById(R.id.expenses_label);
        earnText = view.findViewById(R.id.earnings_label);
        expList = view.findViewById(R.id.expenses_list);
        earnList = view.findViewById(R.id.earnings_list);


        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        /**Listview**/
        List<ExpenseItem> earn = ExpenseItem.ExpensesToList(
                database.getLastMonthStable(DBHelper.TYPE_INC));
        List<ExpenseItem> exp = ExpenseItem.ExpensesToList(
                database.getLastMonthStable(DBHelper.TYPE_EXP));

        ClickableListManager.clickableExpenseList(expList, exp);
        ClickableListManager.clickableExpenseList(earnList, earn);

        ExpenseAdapter expenseAdapter = new ExpenseAdapter(earn);
        earnList.setAdapter(expenseAdapter);
        expList.setAdapter(expenseAdapter);

        earnList.setEnabled(false);
        expList.setEnabled(false);

        if(exp.isEmpty()) expText.setText("");
        if(earn.isEmpty()) earnText.setText("");
        if(exp.isEmpty() && earn.isEmpty()) {
            CharSequence text = "No stable expenses";
            expText.setText(text);
        }
    }
}
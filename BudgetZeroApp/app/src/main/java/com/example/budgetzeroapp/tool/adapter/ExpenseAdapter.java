package com.example.budgetzeroapp.tool.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.budgetzeroapp.AppContext;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.item.ExpenseItem;

import java.util.List;


public class ExpenseAdapter extends ArrayAdapter<ExpenseItem> {

    protected int resourceLayout;

    public ExpenseAdapter(List<ExpenseItem> items) {
        super(AppContext.getContext(), R.layout.expense, items);
        resourceLayout = R.layout.expense;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(AppContext.getContext());
            v = vi.inflate(resourceLayout, null);
        }
        ExpenseItem p = getItem(position);
        if (p != null) {
            TextView label = v.findViewById(R.id.label);
            if (label != null) label.setText(p.getName());
            TextView catName = v.findViewById(R.id.category_name);
            if (catName != null) catName.setText(p.getCategoryName());
            TextView amount = v.findViewById(R.id.amount);
            if (amount != null) {
                String mess = p.getAmount() + " €";
                amount.setText(mess);
            }
        }
        return v;
    }
}
package com.example.budgetzeroapp.tool.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;

import com.example.budgetzeroapp.AppVars;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.item.CategoryItem;

import java.util.List;

public class BudgetAdapter extends ArrayAdapter<CategoryItem> {

    protected int resourceLayout;
    public BudgetAdapter(List<CategoryItem> items) {
        super(AppVars.getContext(), R.layout.category, items);
        resourceLayout = R.layout.category;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(AppVars.getContext());
            v = vi.inflate(resourceLayout, null);
        }
        CategoryItem p = getItem(position);
        if (p != null) {
            TextView catName = (TextView) v.findViewById(R.id.category_name);
            if (catName != null) catName.setText(p.getName());
            TextView amount = (TextView) v.findViewById(R.id.amount);
            if (amount != null) {
                String mess = p.getTotal() + " €";
                amount.setText(mess);
            }
            ImageView icon = v.findViewById(R.id.icon);
            if (icon != null)
                icon.setImageDrawable(AppCompatResources.getDrawable(AppVars.getContext(), p.getDrawable()));
            TextView budget = (TextView) v.findViewById(R.id.budget);
            if (budget != null) {
                String mess = "Budget : "+p.getBudget() + " €";
                budget.setText(mess);
            }
        }
        return v;
    }
}

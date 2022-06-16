package com.example.zeroday.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.zeroday.R;
import com.example.zeroday.models.IncomeCategory;

import java.util.List;

public class GridIncomeAdapter extends BaseAdapter {
    private Context context;
    private List<IncomeCategory> categoriesList;
    private LayoutInflater inflater;

    public  GridIncomeAdapter(Context context, List<IncomeCategory> categoriesList)
    {
        this.context = context;
        this.categoriesList = categoriesList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return categoriesList.size();
    }

    @Override
    public IncomeCategory getItem(int position) {
        return categoriesList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return categoriesList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.category_expenses_incomes_adapter,null);
        IncomeCategory currentCategory = getItem(i);
        String label = currentCategory.getLabelIncomeCategory();
        String code = currentCategory.getCodeIncomeCategory();

        ImageView itemPicture = view.findViewById(R.id.category_circle);
        TextView itemLabel = view.findViewById(R.id.category_name);
        itemLabel.setText(label);

        switch (code)
        {
            case "cat-inc-salary":
                itemPicture.setImageResource(R.drawable.mainincome);
                itemLabel.setTextColor(ContextCompat.getColor(inflater.getContext(), R.color.salary_color));
                break;
            case "cat-inc-bonus":
                itemPicture.setImageResource(R.drawable.bonus);
                itemLabel.setTextColor(ContextCompat.getColor(inflater.getContext(), R.color.bonus_color));
                break;
            case "cat-inc-gift":
                itemPicture.setImageResource(R.drawable.gift);
                itemLabel.setTextColor(ContextCompat.getColor(inflater.getContext(), R.color.gifts_color));
                break;
            case "cat-inc-loc":
                itemPicture.setImageResource(R.drawable.location);
                itemLabel.setTextColor(ContextCompat.getColor(inflater.getContext(), R.color.location_color));
                break;
            default:
                itemPicture.setImageResource(R.drawable.custom_income);
                itemLabel.setTextColor(ContextCompat.getColor(inflater.getContext(), R.color.custom_income_color));
                break;
        }


        return view;
    }
}

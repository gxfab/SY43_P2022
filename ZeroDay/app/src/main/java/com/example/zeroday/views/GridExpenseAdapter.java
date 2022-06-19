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
import com.example.zeroday.models.ExpenseCategory;

import java.util.List;

public class GridExpenseAdapter extends BaseAdapter {

    private Context context;
    private List<ExpenseCategory> categoriesList;
    private LayoutInflater inflater;

    public  GridExpenseAdapter(Context context, List<ExpenseCategory> categoriesList)
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
    public ExpenseCategory getItem(int position) {
        return categoriesList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return categoriesList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.category_expenses_incomes_adapter,null);
        ExpenseCategory currentCategory = getItem(i);
        String label = currentCategory.getLabelExpenseCategory();
        String code = currentCategory.getCodeExpenseCategory();

        ImageView itemPicture = view.findViewById(R.id.category_circle);
        TextView itemLabel = view.findViewById(R.id.category_name);
        itemLabel.setText(label);

        switch (code)
        {
            case "cat-exp-foo":
                itemPicture.setImageResource(R.drawable.food);
                itemLabel.setTextColor(ContextCompat.getColor(inflater.getContext(), R.color.food_color));
                break;
            case "cat-exp-tran":
                itemPicture.setImageResource(R.drawable.transport);
                itemLabel.setTextColor(ContextCompat.getColor(inflater.getContext(), R.color.transports_color));
                break;
            case "cat-exp-ent":
                itemPicture.setImageResource(R.drawable.hobby);
                itemLabel.setTextColor(ContextCompat.getColor(inflater.getContext(), R.color.hobbies_color));
                break;
            case "cat-exp-hea":
                itemPicture.setImageResource(R.drawable.health);
                itemLabel.setTextColor(ContextCompat.getColor(inflater.getContext(), R.color.health_color));
                break;
            case "cat-exp-hou":
                itemPicture.setImageResource(R.drawable.house);
                itemLabel.setTextColor(ContextCompat.getColor(inflater.getContext(), R.color.house_color));
                break;
            case "cat-exp-com":
                itemPicture.setImageResource(R.drawable.communications);
                itemLabel.setTextColor(ContextCompat.getColor(inflater.getContext(), R.color.communications_color));
                break;
            case "cat-exp-ext":
                itemPicture.setImageResource(R.drawable.extras);
                itemLabel.setTextColor(ContextCompat.getColor(inflater.getContext(), R.color.extras_color));
                break;
            default:
                itemPicture.setImageResource(R.drawable.custom_expense);
                itemLabel.setTextColor(ContextCompat.getColor(inflater.getContext(), R.color.custom_expense_color));
                break;
        }


        return view;
    }
}

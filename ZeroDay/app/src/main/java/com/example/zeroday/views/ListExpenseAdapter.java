package com.example.zeroday.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.zeroday.R;
import com.example.zeroday.models.Expense;



import java.util.List;

//Adapter permettant d'afficher chaque dépense dans une catégorie donnée
public class ListExpenseAdapter extends BaseAdapter {

    private Context context;
    private List<Expense> expensesList;
    private LayoutInflater inflater;

    public ListExpenseAdapter(Context context, List<Expense> expensesList)
    {
        this.context = context;
        this.expensesList = expensesList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return expensesList.size();
    }

    @Override
    public Expense getItem(int position) {
        return expensesList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return expensesList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.income_expense_details_adapter,null);
        Expense currentExpense = getItem(i);
        String label = currentExpense.getLabelExpense();
        String frequency = currentExpense.getFrequenceExpense().toString();
        String amount = currentExpense.getAmountExpense().toString();
        String category = currentExpense.getCategoryExpense().getCodeExpenseCategory();

        TextView itemLabel = view.findViewById(R.id.details_title);
        TextView itemFrequency = view.findViewById(R.id.details_frequency);
        TextView itemAmount = view.findViewById(R.id.details_price);
        LinearLayout itemLayout = view.findViewById(R.id.details_background);

        itemLabel.setText(label);
        itemFrequency.setText(frequency);
        itemAmount.setText(amount);

        switch (category)
        {
            case "cat-exp-foo":
                itemLayout.setBackgroundColor(ContextCompat.getColor(inflater.getContext(), R.color.food_color));
                break;
            case "cat-exp-tran":
                itemLayout.setBackgroundColor(ContextCompat.getColor(inflater.getContext(), R.color.transports_color));
                break;
            case "cat-exp-ent":
                itemLayout.setBackgroundColor(ContextCompat.getColor(inflater.getContext(), R.color.hobbies_color));
                break;
            case "cat-exp-hea":
                itemLayout.setBackgroundColor(ContextCompat.getColor(inflater.getContext(), R.color.health_color));
                break;
            case "cat-exp-hou":
                itemLayout.setBackgroundColor(ContextCompat.getColor(inflater.getContext(), R.color.house_color));
                break;
            case "cat-exp-com":
                itemLayout.setBackgroundColor(ContextCompat.getColor(inflater.getContext(), R.color.communications_color));
                break;
            case "cat-exp-ext":
                itemLayout.setBackgroundColor(ContextCompat.getColor(inflater.getContext(), R.color.extras_color));
                break;
            default:
                itemLayout.setBackgroundColor(ContextCompat.getColor(inflater.getContext(), R.color.custom_expense_color));
                break;
        }


        return view;
    }
}

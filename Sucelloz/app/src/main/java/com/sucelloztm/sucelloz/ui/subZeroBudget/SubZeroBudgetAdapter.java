package com.sucelloztm.sucelloz.ui.subZeroBudget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.models.StableExpensesAndIncome;

import java.util.List;

public class SubZeroBudgetAdapter extends RecyclerView.Adapter<SubZeroBudgetAdapter.ViewHolder> {
    private List<StableExpensesAndIncome> stableExpensesAndIncomeList;

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        private final TextView nameTextView;
        private final TextView dateTextView;
        private final TextView amountTextView;

        public ViewHolder(@NonNull View view) {
            super(view);
            nameTextView = view.findViewById(R.id.stable_name_text_view);
            dateTextView = view.findViewById(R.id.stable_date_text_view);
            amountTextView = view.findViewById(R.id.stable_amount_text_view);

        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getDateTextView() {
            return dateTextView;
        }

        public TextView getAmountTextView() {
            return amountTextView;
        }
    }

    public SubZeroBudgetAdapter(List<StableExpensesAndIncome> dataSet) { this.stableExpensesAndIncomeList = dataSet; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sub_zero_budget_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        StableExpensesAndIncome currentStable = stableExpensesAndIncomeList.get(position);
        viewHolder.getNameTextView().setText(currentStable.getName());
        String amount = "" + currentStable.getSign() + currentStable.getAmount() + "€";
        viewHolder.getAmountTextView().setText(amount);
        viewHolder.getDateTextView().setText(currentStable.getDate());
    }

    @Override
    public int getItemCount() {
        return stableExpensesAndIncomeList.size();
    }


}

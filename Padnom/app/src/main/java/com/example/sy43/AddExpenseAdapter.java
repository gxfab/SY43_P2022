package com.example.sy43;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AddExpenseAdapter extends RecyclerView.Adapter<AddExpenseAdapter.ExpenseViewHolder> {

    private List<String> expense_name, expense_amount,dates,subcategories;

    public AddExpenseAdapter(List<String> dates, List<String> expense_name, List<String> expense_amount, List<String> subcategories){
        this.dates=dates;
        this.expense_name=expense_name;
        this.expense_amount=expense_amount;
        this.subcategories = subcategories;
    }


    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        holder.dtv.setText(dates.get(position));
        holder.ntv.setText(expense_name.get(position));
        holder.atv.setText(expense_amount.get(position));
        holder.stv.setText(subcategories.get(position));
    }

    @Override
    public int getItemCount() {
        return expense_name.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder{

        private TextView dtv, stv, atv, ntv;
        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);

            dtv= itemView.findViewById(R.id.latest_expense_date);
            ntv= itemView.findViewById(R.id.latest_expense_name);
            atv= itemView.findViewById(R.id.latest_expense_value);
            stv = itemView.findViewById(R.id.latest_expense_subcategory);
        }
    }
}

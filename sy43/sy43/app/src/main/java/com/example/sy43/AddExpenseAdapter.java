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

    private List<String> expense_name, expense_amount,dates;

    public AddExpenseAdapter(List<String> dates, List<String> expense_name, List<String> expense_amount){
        this.dates=dates;
        this.expense_name=expense_name;
        this.expense_amount=expense_amount;
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
        holder.stv.setText(expense_name.get(position));
        holder.atv.setText(expense_amount.get(position));
    }

    @Override
    public int getItemCount() {
        return expense_name.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder{

        private TextView dtv, stv, atv;
        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);

            dtv= itemView.findViewById(R.id.latest_expense_date);
            stv= itemView.findViewById(R.id.latest_expense_name);
            atv= itemView.findViewById(R.id.latest_expense_value);
        }
    }
}

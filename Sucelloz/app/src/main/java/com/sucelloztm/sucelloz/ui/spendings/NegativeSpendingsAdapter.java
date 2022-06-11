package com.sucelloztm.sucelloz.ui.spendings;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.R;

import java.util.List;

public class NegativeSpendingsAdapter extends RecyclerView.Adapter<NegativeSpendingsAdapter.ViewHolder> {

    private List<Spendings> negativeSpendingsList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView nameTextView;
        private final TextView dateTextView;
        private final TextView subCategoryTextView;
        private final TextView amountTextView;

        public ViewHolder(@NonNull View view) {
            super(view);

            nameTextView=view.findViewById(R.id.spendings_name_text_view);
            dateTextView=view.findViewById(R.id.spendings_date_text_view);
            subCategoryTextView=view.findViewById(R.id.spendings_subcategory_text_view);
            amountTextView=view.findViewById(R.id.spendings_amount_text_view);
            amountTextView.setTextColor(Color.parseColor("#FF4444"));
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getDateTextView() {
            return dateTextView;
        }

        public TextView getSubCategoryTextView() {
            return subCategoryTextView;
        }

        public TextView getAmountTextView() {
            return amountTextView;
        }
    }

    public NegativeSpendingsAdapter(List<Spendings> dataSet) { this.negativeSpendingsList = dataSet; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.negative_spendings_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NegativeSpendingsAdapter.ViewHolder viewHolder, int position) {
        viewHolder.getNameTextView().setText(negativeSpendingsList.get(position).getName());
        String amount = "-" + negativeSpendingsList.get(position).getAmount() + "€";
        viewHolder.getAmountTextView().setText(amount);
        viewHolder.getDateTextView().setText(negativeSpendingsList.get(position).getDate());
        viewHolder.getSubCategoryTextView().setText(negativeSpendingsList.get(position).getSubCategory());
    }

    @Override
    public int getItemCount() {
        return negativeSpendingsList.size();
    }

}

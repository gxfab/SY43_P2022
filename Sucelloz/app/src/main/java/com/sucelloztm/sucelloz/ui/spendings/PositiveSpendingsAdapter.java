package com.sucelloztm.sucelloz.ui.spendings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.models.InfrequentExpensesAndIncome;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;
import com.sucelloztm.sucelloz.ui.subcategories.SubCategoriesAdapter;

import java.util.ArrayList;
import java.util.List;

public class PositiveSpendingsAdapter extends RecyclerView.Adapter<PositiveSpendingsAdapter.ViewHolder> {
    private List<Spendings> positiveSpendingsList;

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

    public PositiveSpendingsAdapter(List<Spendings> dataSet) { this.positiveSpendingsList = dataSet; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.spendings_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.getNameTextView().setText(positiveSpendingsList.get(position).getName());
        String amount = positiveSpendingsList.get(position).getAmount() + "€";
        viewHolder.getAmountTextView().setText(amount);
        viewHolder.getDateTextView().setText(positiveSpendingsList.get(position).getDate());
        viewHolder.getSubCategoryTextView().setText(positiveSpendingsList.get(position).getSubCategory());
    }

    @Override
    public int getItemCount() {
        return positiveSpendingsList.size();
    }


}

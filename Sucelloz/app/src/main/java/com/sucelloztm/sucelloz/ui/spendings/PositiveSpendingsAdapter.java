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

/**
 * adapter for the positive spendings
 */
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
            amountTextView.setTextColor(Color.parseColor("#99CC00"));
        }

        /**
         * getter
         * @return name
         */
        public TextView getNameTextView() {
            return nameTextView;
        }

        /**
         * getter
         * @return date
         */
        public TextView getDateTextView() {
            return dateTextView;
        }

        /**
         * getter
         * @return subcategory name
         */
        public TextView getSubCategoryTextView() {
            return subCategoryTextView;
        }

        /**
         * getter
         * @return amount
         */
        public TextView getAmountTextView() {
            return amountTextView;
        }
    }

    /**
     * custom constructor
     * @param dataSet list of spendings
     */
    public PositiveSpendingsAdapter(List<Spendings> dataSet) { this.positiveSpendingsList = dataSet; }

    /**
     * on create view holder method
     * @param viewGroup view group
     * @param viewType view type
     * @return view holder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.negative_spendings_item, viewGroup, false);

        return new ViewHolder(view);
    }

    /**
     * on bind view holder method
     * @param viewHolder view holder
     * @param position position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.getNameTextView().setText(positiveSpendingsList.get(position).getName());
        String amount = "+" + positiveSpendingsList.get(position).getAmount() + "€";
        viewHolder.getAmountTextView().setText(amount);
        viewHolder.getDateTextView().setText(positiveSpendingsList.get(position).getDate());
        viewHolder.getSubCategoryTextView().setText(positiveSpendingsList.get(position).getSubCategory());
    }

    /**
     * getter
     * @return item count
     */
    @Override
    public int getItemCount() {
        return positiveSpendingsList.size();
    }


}

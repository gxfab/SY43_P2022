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
 * adapter for negative spendings
 */
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
    public NegativeSpendingsAdapter(List<Spendings> dataSet) { this.negativeSpendingsList = dataSet; }

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
    public void onBindViewHolder(@NonNull NegativeSpendingsAdapter.ViewHolder viewHolder, int position) {
        viewHolder.getNameTextView().setText(negativeSpendingsList.get(position).getName());
        String amount = "-" + negativeSpendingsList.get(position).getAmount() + "€";
        viewHolder.getAmountTextView().setText(amount);
        viewHolder.getDateTextView().setText(negativeSpendingsList.get(position).getDate());
        viewHolder.getSubCategoryTextView().setText(negativeSpendingsList.get(position).getSubCategory());
    }

    /**
     * getter
     * @return item count
     */
    @Override
    public int getItemCount() {
        return negativeSpendingsList.size();
    }

}

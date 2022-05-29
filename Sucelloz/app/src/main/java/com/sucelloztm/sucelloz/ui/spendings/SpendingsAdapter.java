package com.sucelloztm.sucelloz.ui.spendings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.R;

import java.util.ArrayList;

public class SpendingsAdapter extends RecyclerView.Adapter<SpendingsAdapter.ViewHolder> {

    private final ArrayList<Spendings> localDataSet;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView dateTextView;
        private final TextView amountTextView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            nameTextView = view.findViewById(R.id.spendings_name_text_view);
            dateTextView = view.findViewById(R.id.spendings_date_text_view);
            amountTextView = view.findViewById(R.id.spendings_amount_text_view);
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

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView.
     */
    public SpendingsAdapter(ArrayList<Spendings> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public SpendingsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.spendings_item, viewGroup, false);

        return new SpendingsAdapter.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(SpendingsAdapter.ViewHolder viewHolder, final int position) {
        Spendings spendings = localDataSet.get(position);
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getNameTextView().setText(spendings.getName());
        viewHolder.getDateTextView().setText(spendings.getDate());
        viewHolder.getAmountTextView().setText(spendings.getAmount());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

}

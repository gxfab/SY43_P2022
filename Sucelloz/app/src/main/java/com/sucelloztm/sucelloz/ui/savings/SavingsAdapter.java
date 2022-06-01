package com.sucelloztm.sucelloz.ui.savings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.sucelloztm.sucelloz.R;

import java.util.ArrayList;

public class SavingsAdapter extends RecyclerView.Adapter<SavingsAdapter.ViewHolder> {

    private final ArrayList<Savings> localDataSet;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView deadlineTextView;
        private final TextView amountTextView;
        private final ProgressBar progressBar;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            nameTextView = view.findViewById(R.id.savings_name_text_view);
            deadlineTextView = view.findViewById(R.id.savings_deadline_text_view);
            amountTextView = view.findViewById(R.id.savings_amount_text_view);
            progressBar = view.findViewById(R.id.savings_progress_bar);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getDeadlineTextView() {
            return deadlineTextView;
        }

        public TextView getAmountTextView() {
            return amountTextView;
        }

        public ProgressBar getProgressBar() {
            return progressBar;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView.
     */
    public SavingsAdapter(ArrayList<Savings> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.savings_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Savings savings = localDataSet.get(position);
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getNameTextView().setText(savings.getName());
        viewHolder.getDeadlineTextView().setText(savings.getDeadline());
        String amount = savings.getReachedAmount()+"/"+ savings.getInitialAmount()+"€";
        viewHolder.getAmountTextView().setText(amount);
        viewHolder.getProgressBar().setProgress((int)savings.getPercentage(),true);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

}

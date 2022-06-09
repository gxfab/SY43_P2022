package com.sucelloztm.sucelloz.ui.savings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.models.Savings;

import java.util.ArrayList;
import java.util.List;

public class SavingsAdapter extends RecyclerView.Adapter<SavingsAdapter.ViewHolder> {

    private List<Savings> savingsList;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView deadlineTextView;
        private final TextView goalTextView;
        private final ProgressBar progressBar;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            nameTextView = view.findViewById(R.id.savings_name_text_view);
            deadlineTextView = view.findViewById(R.id.savings_deadline_text_view);
            goalTextView = view.findViewById(R.id.savings_goal_text_view);
            progressBar = view.findViewById(R.id.savings_progress_bar);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getDeadlineTextView() {
            return deadlineTextView;
        }

        public TextView getGoalTextView() {
            return goalTextView;
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
    public SavingsAdapter(List<Savings> dataSet) {
        this.savingsList = dataSet;
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
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getNameTextView().setText(savingsList.get(position).getName());
        viewHolder.getDeadlineTextView().setText(savingsList.get(position).getDeadline());
        String amount = savingsList.get(position).getReachedAmount()+"/"+ savingsList.get(position).getInitialAmount()+"€";
        viewHolder.getGoalTextView().setText(amount);
        viewHolder.getProgressBar().setProgress((int)savingsList.get(position).getPercentage(),true);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return savingsList.size();
    }

}

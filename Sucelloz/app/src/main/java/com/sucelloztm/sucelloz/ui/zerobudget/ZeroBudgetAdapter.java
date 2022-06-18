package com.sucelloztm.sucelloz.ui.zerobudget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.ui.subZeroBudget.SubZeroBudgetAdapter;

import java.util.List;
/**
 * adapter for the zero budget
 */
public class ZeroBudgetAdapter extends RecyclerView.Adapter<ZeroBudgetAdapter.ViewHolder> {

    private List<SubCategories> subCategoriesList;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = view.findViewById(R.id.zero_budget_text_view);
        }

        public TextView getTextView() {
            return textView;
        }
    }


    public ZeroBudgetAdapter(List<SubCategories> dataSet) {
        this.subCategoriesList = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ZeroBudgetAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.zero_budget_item, viewGroup, false);
        return new ZeroBudgetAdapter.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ZeroBudgetAdapter.ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
       viewHolder.getTextView().setText(subCategoriesList.get(position).getName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return subCategoriesList.size();
    }

}

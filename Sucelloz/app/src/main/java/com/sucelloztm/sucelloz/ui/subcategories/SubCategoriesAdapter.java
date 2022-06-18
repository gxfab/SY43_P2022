package com.sucelloztm.sucelloz.ui.subcategories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.models.SubCategories;

import java.util.List;


/**
 * adapter for the subcategories
 */
public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.ViewHolder> {

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

            textView = view.findViewById(R.id.text_view_subcategories);
        }

        /**
         * getter
         * @return text
         */
        public TextView getTextView() {
            return textView;
        }
    }


    /**
     * custom constructor
     * @param dataSet list of the subcategories
     */
    public SubCategoriesAdapter(List<SubCategories> dataSet) {
        this.subCategoriesList = dataSet;
    }


    /**
     * Create new views (invoked by the layout manager)
     * @param viewGroup view group
     * @param viewType view type
     * @return view holder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sub_categories_item, viewGroup, false);
        return new ViewHolder(view);
    }


    /**
     * Replace the contents of a view (invoked by the layout manager)
     * @param viewHolder view holder
     * @param position position
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(subCategoriesList.get(position).getName());
        
    }


    /**
     * Return the size of your dataset (invoked by the layout manager)
     * @return item count
     */
    @Override
    public int getItemCount() {
        return subCategoriesList.size();
    }

}

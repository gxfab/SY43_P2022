package com.sucelloztm.sucelloz.ui.categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;



import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.repositories.CategoriesRepository;

import java.util.List;


/**
 * adapter for the categories
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private List<Categories> categoriesList;





    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;


        /**
         * custom constructor
         * @param view current view
         */
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = view.findViewById(R.id.text_view_categories);
        }

        /**
         * getter
         * @return text view
         */
        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * custom constructor
     * @param dataSet list of categories
     */
    public CategoriesAdapter(List<Categories> dataSet) {
        this.categoriesList = dataSet;
    }

    /**
     * Create new views (invoked by the layout manager)
     * @param viewGroup view group
     * @param viewType view type
     * @return created view of the desired group and type
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.categories_item, viewGroup, false);

        return new ViewHolder(view);
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     * @param viewHolder view
     * @param position position
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(categoriesList.get(position).getName());
    }

    /**
     * Return the size of the dataset (invoked by the layout manager)
     * getter
     * @return size of the dataset
     */
    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

}

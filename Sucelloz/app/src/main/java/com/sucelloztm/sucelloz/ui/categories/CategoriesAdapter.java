package com.sucelloztm.sucelloz.ui.categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;



import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.repositories.CategoriesRepository;

import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private List<Categories> categoriesList;



    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final CardView cardView;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = view.findViewById(R.id.text_view_categories);
            cardView = view.findViewById(R.id.category_card_clickable);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public CategoriesAdapter(List<Categories> dataSet) {
        this.categoriesList = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.categories_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(categoriesList.get(position).getName());
        //TODO:fix bug when clicking two categories at the same time
        viewHolder.itemView.findViewById(R.id.category_card_clickable).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_categories_to_navigation_sub_categories));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

}

package com.example.nomoola.adapter;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.viewHolder.SubcategoryViewHolder;
import com.example.nomoola.viewModel.SubcategoryViewModel;

public class SubcategoryAdapter extends ListAdapter<SubCategory, SubcategoryViewHolder> {

    private FragmentManager fragmentManager;
    private SubcategoryViewModel subcategoryViewModel;

    public SubcategoryAdapter(@NonNull DiffUtil.ItemCallback<SubCategory> diffCallback, FragmentManager fragmentManager, SubcategoryViewModel subcategoryViewModel) {
        super(diffCallback);
        this.fragmentManager = fragmentManager;
        this.subcategoryViewModel = subcategoryViewModel;
    }

    @NonNull
    @Override
    public SubcategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("CREATION", "onCreateViewHolder from " + this.getClass().toString() + " started");

        SubcategoryViewHolder viewHolder = SubcategoryViewHolder.create(parent, this.fragmentManager, this.subcategoryViewModel);

        Log.d("CREATION", "onCreateViewHolder from " + this.getClass().toString() + " finished");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubcategoryViewHolder holder, int position) {
        Log.d("CREATION", "onBindViewHolder from " + this.getClass().toString() + " started");
        SubCategory currentSubcat = getItem(position);
        holder.bind(currentSubcat);
        Log.d("CREATION", "onBindViewHolder from " + this.getClass().toString() + " finished");
    }

    public static class SubcategoryDiff extends DiffUtil.ItemCallback<SubCategory>{

        @Override
        public boolean areItemsTheSame(@NonNull SubCategory oldItem, @NonNull SubCategory newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull SubCategory oldItem, @NonNull SubCategory newItem) {
            return oldItem.getM_SUBCAT_ID() == newItem.getM_SUBCAT_ID();
        }
    }
}

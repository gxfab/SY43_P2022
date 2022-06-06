package com.example.nomoola.adapter;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.viewHolder.SubcategoryViewHolder;

public class SubcategoryAdapter extends ListAdapter<SubCategory, SubcategoryViewHolder> {

    private FragmentManager fragmentManager;

    public SubcategoryAdapter(@NonNull DiffUtil.ItemCallback<SubCategory> diffCallback, FragmentManager fragmentManager) {
        super(diffCallback);
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public SubcategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("CREATION", "onCreateViewHolder from " + this.getClass().toString() + " started");

        Log.d("CREATION", "onCreateViewHolder from " + this.getClass().toString() + " finished");
        return SubcategoryViewHolder.create(parent, this.fragmentManager);
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

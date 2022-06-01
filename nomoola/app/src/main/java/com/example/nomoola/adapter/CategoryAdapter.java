package com.example.nomoola.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomoola.R;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.viewHolder.CategoryViewHolder;

import java.util.ArrayList;

public class CategoryAdapter extends ListAdapter<Category, CategoryViewHolder> {

    private FragmentManager fragmentManager;

    public CategoryAdapter(@NonNull DiffUtil.ItemCallback<Category> diffCallback, FragmentManager fragmentManager) {
        super(diffCallback);
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("CREATION", "onCreateViewHolder from " + this.getClass().toString() + " started");

        Log.d("CREATION", "onCreateViewHolder from " + this.getClass().toString() + " finished");
        return CategoryViewHolder.create(parent, this.fragmentManager);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Log.d("CREATION", "onBindViewHolder from " + this.getClass().toString() + " started");
        Category currentCat = getItem(position);
        holder.bind(currentCat);

        Log.d("CREATION", "onBindViewHolder from " + this.getClass().toString() + " finished");
    }

    public static class CategoryDiff extends DiffUtil.ItemCallback<Category>{

        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getM_CAT_NAME().equals(newItem.getM_CAT_NAME());
        }
    }
}

package com.example.econo_misons.views;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.econo_misons.database.models.Category;

import java.util.List;

public class DepenseAdapter extends RecyclerView.Adapter<DepenseViewHolder> {

    public List<Category> categories;

    public void updateCategories(List<Category> categoryList){
        categories = categoryList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DepenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DepenseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

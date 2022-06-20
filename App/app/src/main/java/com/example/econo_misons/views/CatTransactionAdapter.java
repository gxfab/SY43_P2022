package com.example.econo_misons.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.econo_misons.database.models.Category;
import com.example.econo_misons.databinding.ItemCategoryBinding;

import java.util.ArrayList;
import java.util.List;

public class CatTransactionAdapter extends RecyclerView.Adapter<CatTransactionViewHolder>{

    private List<Category> categoryList;

    public CatTransactionAdapter(){
        this.categoryList = new ArrayList<>();
    }

    // Sets the context for the created view holder
    @NonNull
    @Override
    public CatTransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return new CatTransactionViewHolder(ItemCategoryBinding.inflate(inflater,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CatTransactionViewHolder holder, int position) {
        holder.initializeItem(this.categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    // returns the number of items in the recycler view
    public Category getCat(int position){
        return categoryList.get(position);
    }

    // updates the categories in the local list
    public void updateList(List<Category> categories){
        this.categoryList = categories;
        this.notifyDataSetChanged();
    }
}

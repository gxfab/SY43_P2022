package com.example.econo_misons.views;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.econo_misons.database.models.Budget;
import com.example.econo_misons.database.models.Category;
import com.example.econo_misons.databinding.ItemCategoryBinding;

import java.lang.ref.WeakReference;

public class CatTransactionViewHolder extends RecyclerView.ViewHolder{

    private final ItemCategoryBinding binding;

    public CatTransactionViewHolder(ItemCategoryBinding binding){
        super(binding.getRoot());
        this.binding = binding;
    }

    public void initializeItem(Category cat){
        binding.text.setText(cat.categoryName);
    }

}

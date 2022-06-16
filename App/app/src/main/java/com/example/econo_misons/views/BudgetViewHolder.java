package com.example.econo_misons.views;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.econo_misons.database.models.Budget;
import com.example.econo_misons.databinding.ItemBudgetBinding;

import java.lang.ref.WeakReference;

public class BudgetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final ItemBudgetBinding binding;
    private WeakReference<BudgetAdapter.Listener> callbackWeakRef;

    public BudgetViewHolder(ItemBudgetBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void initializeItem(Budget budget, BudgetAdapter.Listener callback){
        binding.budgetName.setText(budget.budgetName);
        binding.removeBudget.setOnClickListener(this);
        this.callbackWeakRef = new WeakReference<BudgetAdapter.Listener>(callback);
    }


    @Override
    public void onClick(View view) {
        // 5 - When a click happens, we fire our listener.
        BudgetAdapter.Listener callback = callbackWeakRef.get();
        if (callback != null){
            callback.onClickDeleteButton(getAdapterPosition());
        }
    }

}

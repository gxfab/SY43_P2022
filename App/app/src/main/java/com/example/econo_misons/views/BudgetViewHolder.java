package com.example.econo_misons.views;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.econo_misons.database.CurrentData;
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

    private void setBudget(Budget budget){
        CurrentData.setBudget(budget);
        showCurrentBudget(CurrentData.getBudget());
    }

    private void remove_budget(Budget budget){
        if (CurrentData.getBudget() != budget){
            //TODO remove from database
        }
    }

    private void showCurrentBudget(Budget budget){
        Toast toast = Toast.makeText(itemView.getContext(), budget.toString(), Toast.LENGTH_LONG);
        toast.show();
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

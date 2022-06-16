package com.example.econo_misons.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.econo_misons.database.models.Budget;
import com.example.econo_misons.database.models.PrevisionalBudget;
import com.example.econo_misons.databinding.ItemBudgetBinding;

import java.util.ArrayList;
import java.util.List;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetViewHolder> {

    public interface Listener {
        void onClickDeleteButton(int position);
    }

    private final Listener callback;
    private List<Budget> budgets;
    public List<PrevisionalBudget> previsionalBudgets;

    public BudgetAdapter(Listener callback){
        this.budgets = new ArrayList<>();
        this.callback = callback;
    }

    @Override
    public BudgetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return new BudgetViewHolder(ItemBudgetBinding.inflate(inflater,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetViewHolder holder, int position) {
        holder.initializeItem(this.budgets.get(position),this.callback);
    }

    @Override
    public int getItemCount() {
        return this.budgets.size();
    }

    public void updateData(List<Budget> budgets){
        this.budgets = budgets;
        this.notifyDataSetChanged();
    }

    public void updatePrevBudgets(List<PrevisionalBudget> budgets){
        this.previsionalBudgets = budgets;
        this.notifyDataSetChanged();
    }

    public Budget getBudget(int position){
        return this.budgets.get(position);
    }
}

package com.example.econo_misons.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.econo_misons.database.models.Category;
import com.example.econo_misons.database.models.PrevisionalBudget;
import com.example.econo_misons.database.models.Transaction;
import com.example.econo_misons.databinding.ItemTransactionBinding;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionViewHolder>{

    // Custom listener
    public interface Listener {
        void onClickModifyButton(int position);
        void onClickDeleteButton(int position);
    }

    private final TransactionAdapter.Listener callback;
    public List<Transaction> transactions;
    public List<Category> categories;
    public Category category;

    // initializing adapter
    public TransactionAdapter(TransactionAdapter.Listener callback){
        this.transactions = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.callback = callback;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return new TransactionViewHolder(ItemTransactionBinding.inflate(inflater,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        holder.initializeItem(this.transactions.get(position),this.callback);
    }

    // return the number of items int the recyclerview
    @Override
    public int getItemCount() {
        return this.transactions.size();
    }

    // gets all transaction
    public void getAllTransactions(List<Transaction> transactionList) {
        this.transactions = transactionList;
    }

    // get the list of transaction for the current category
    public void getSomeTransactions(List<Transaction> transactionList) {
        this.transactions = transactionList;

        if (!this.transactions.isEmpty()) {
            int index = 0;
            List<Transaction> toRemove = new ArrayList<>();
            for (Transaction trans : this.transactions) {
                if (trans.categoryID != this.category.id) {
                    toRemove.add(this.transactions.get(index));
                }
                index++;
            }
            this.transactions.removeAll(toRemove);
        }
        this.notifyDataSetChanged();
    }

    // update the categories in the local list
    public void getCategories(List<Category> categories) {
        this.categories = categories;
        this.notifyDataSetChanged();
    }

    // returns the transaction in position "position"
    public Transaction getTransaction(int position) {
        return this.transactions.get(position);
    }
}

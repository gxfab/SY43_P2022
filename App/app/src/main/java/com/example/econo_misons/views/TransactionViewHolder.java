package com.example.econo_misons.views;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.econo_misons.R;
import com.example.econo_misons.database.models.Transaction;
import com.example.econo_misons.databinding.ItemTransactionBinding;

import java.lang.ref.WeakReference;

public class TransactionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final ItemTransactionBinding binding;
    private WeakReference<TransactionAdapter.Listener> callbackWeakRef;

    public TransactionViewHolder(ItemTransactionBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void initializeItem(Transaction transaction, TransactionAdapter.Listener callback){
        binding.transName.setText(transaction.transactionName);
        binding.transDate.setText(transaction.date);

        if (transaction.expense){
            binding.item.setBackgroundColor(Color.RED);
            binding.transAmount.setText("-" + transaction.amountTransaction);

        } else {
            binding.item.setBackgroundColor(Color.GREEN);
            binding.transAmount.setText("+" + transaction.amountTransaction);

        }
        binding.modifyTrans.setOnClickListener(this);
        binding.removeTrans.setOnClickListener(this);
        this.callbackWeakRef = new WeakReference<TransactionAdapter.Listener>(callback);
    }

    @Override
    public void onClick(View view) {
        TransactionAdapter.Listener callback = callbackWeakRef.get();
        if (callback != null){
            Log.e("TVH",view.getId()+"");
            if(view.getId() == R.id.modify_trans){
                callback.onClickModifyButton(getAdapterPosition());
            } else {
                callback.onClickDeleteButton(getAdapterPosition());
            }

        }
    }
}

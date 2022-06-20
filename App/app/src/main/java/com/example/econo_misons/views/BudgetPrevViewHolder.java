package com.example.econo_misons.views;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.econo_misons.database.models.Category;
import com.example.econo_misons.database.models.Envelope;
import com.example.econo_misons.databinding.ItemEnvelopeBinding;

import java.lang.ref.WeakReference;
import java.util.List;

public class BudgetPrevViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final ItemEnvelopeBinding binding;
    private WeakReference<BudgetPrevAdapter.Listener> callbackWeakRef;

    public BudgetPrevViewHolder(ItemEnvelopeBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    // function called for every initialized item in the recycler view
    public void initializeItem(List<Category> categoryList, Envelope envelope, BudgetPrevAdapter.Listener callback){
        binding.envelopeName.setText(categoryList.get(this.getIndexCategory(categoryList,envelope)).categoryName);
        binding.valueEnvelope.setText(String.valueOf(envelope.sumEnv));
        binding.modifyEnvelope.setOnClickListener(this);
        this.callbackWeakRef = new WeakReference<BudgetPrevAdapter.Listener>(callback);
    }

    // Function that returns the index of an envelope in a list
    public int getIndexCategory(List<Category> categoryList, Envelope envelope){
        int index = 0;
        for (Category cat : categoryList) {
            if (cat.id == envelope.categoryID){
                break;
            }
            index++;
        }
        return index;
    }

    // Function that verifies the clicks and tells for which item the click is
    @Override
    public void onClick(View view) {
        BudgetPrevAdapter.Listener callback = callbackWeakRef.get();
        if (callback != null){
            callback.onClickModifyButton(getAdapterPosition());
        }
    }

}

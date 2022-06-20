package com.example.econo_misons.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.econo_misons.database.models.Category;
import com.example.econo_misons.database.models.Envelope;
import com.example.econo_misons.databinding.ItemEnvelopeBinding;

import java.util.ArrayList;
import java.util.List;

public class BudgetPrevAdapter extends RecyclerView.Adapter<BudgetPrevViewHolder> {

    // Creating a custom listener
    public interface Listener {
        void onClickModifyButton(int position);
    }

    private final Listener callback;
    public List<Envelope> envelopes;
    public List<Category> categoryList;

    // initialize the adapter
    public BudgetPrevAdapter(Listener callback){
        this.envelopes = new ArrayList<>();
        this.categoryList = new ArrayList<>();
        this.callback = callback;
    }

    // Sets the context for the created view holder
    @NonNull
    @Override
    public BudgetPrevViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return new BudgetPrevViewHolder(ItemEnvelopeBinding.inflate(inflater,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetPrevViewHolder holder, int position) {
        holder.initializeItem(this.categoryList, this.envelopes.get(position),this.callback);
    }

    // returns the number of items in the recycler view
    @Override
    public int getItemCount() {
        return this.envelopes.size();
    }

    // update the categories in the local list
    public void updateCategories(List<Category> categories){
        this.categoryList = categories;
        this.notifyDataSetChanged();
    }

    // update the envelopes in the local list
    public void updateData(List<Envelope> envelopes){
        this.envelopes = envelopes;
        this.notifyDataSetChanged();
    }

    // function that gets the index of the category of an envelope in a list of categories
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
}

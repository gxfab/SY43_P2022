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

    public interface Listener {
        void onClickModifyButton(int position);
    }

    private final Listener callback;
    public List<Envelope> envelopes;
    public List<Category> categoryList;


    public BudgetPrevAdapter(Listener callback){
        this.envelopes = new ArrayList<>();
        this.categoryList = new ArrayList<>();
        this.callback = callback;
    }

    @Override
    public BudgetPrevViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //View view = inflater.inflate(R.layout.item_budget, parent, false);

        return new BudgetPrevViewHolder(ItemEnvelopeBinding.inflate(inflater,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetPrevViewHolder holder, int position) {
        holder.initializeItem(this.categoryList, this.envelopes.get(position),this.callback);
    }

    @Override
    public int getItemCount() {
        return this.envelopes.size();
    }

    public void updateCategories(List<Category> categories){
        this.categoryList = categories;
        this.notifyDataSetChanged();
    }

    public void updateData(List<Envelope> envelopes){
        this.envelopes = envelopes;
        this.notifyDataSetChanged();
    }
    public Envelope getEnvelope(int position){
        return this.envelopes.get(position);
    }
}

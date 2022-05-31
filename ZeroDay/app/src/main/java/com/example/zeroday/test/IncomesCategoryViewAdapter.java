package com.example.zeroday.test;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zeroday.databinding.FragmentIncomesCategoryBinding;
import com.example.zeroday.models.IncomesCategory;
import com.example.zeroday.repositories.IncomesCategoryRepository;
import com.example.zeroday.test.placeholder.PlaceholderContent.PlaceholderItem;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class IncomesCategoryViewAdapter extends RecyclerView.Adapter<IncomesCategoryViewAdapter.ViewHolder> {

    private final List<IncomesCategory> mValues;

    public IncomesCategoryViewAdapter(List<IncomesCategory> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentIncomesCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.valueOf(Math.toIntExact(mValues.get(position).getIdIncomesCategory())));
        holder.mContentView.setText(mValues.get(position).getLabelIncomesCategory());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public IncomesCategory mItem;

        public ViewHolder(FragmentIncomesCategoryBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
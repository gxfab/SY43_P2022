package com.example.sy43;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.SubcategoryViewHolder> {

    private List<String> mList;
    private List<String> aList;
    private int position;

    public SubcategoryAdapter(List<String> mList,List<String> aList, int position) {
        this.mList = mList;
        this.position = position;
        this.aList =aList;
    }

    @NonNull
    @Override
    public SubcategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategory , parent , false);
        return new SubcategoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SubcategoryViewHolder holder, int position) {
        holder.mTv.setText(mList.get(position));
        holder.aTv.setText(aList.get(position));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class SubcategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView mTv,aTv;

        public SubcategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            aTv = itemView.findViewById(R.id.subcategoryEnveloppe);
            mTv = itemView.findViewById(R.id.subcategoryTV);
        }
    }

    @NonNull
    public Lifecycle getLifecycle() {
        return null;
    }

}


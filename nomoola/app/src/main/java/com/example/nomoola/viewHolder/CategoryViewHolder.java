package com.example.nomoola.viewHolder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nomoola.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView categoryName;

    public CategoryViewHolder(View view){
        super(view);
        Log.d("CREATION", "ViewHolder constructor from " + this.getClass().toString() + " started");
        this.categoryName = view.findViewById(R.id.text_view_category_name); //Name of the category
        Log.d("CREATION", "ViewHolder constructor from " + this.getClass().toString() + " finished");
    }

    public void bind(String categoryName){
        this.categoryName.setText(categoryName);
    }

    public static CategoryViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }
}
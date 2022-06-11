package com.example.sy43;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.NestedViewHolder> {

    private List<String> mList;
    private List<Drawable> imgList;
    private List<CardView> btnList;

    public NestedAdapter(List<String> mList, List<Drawable> imgList, List<CardView> btnList){
        this.mList = mList;
        this.imgList = imgList;
        this.btnList = btnList;
    }
    @NonNull
    @Override
    public NestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nested_item , parent , false);
        return new NestedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NestedViewHolder holder, int position) {
        holder.mTv.setText(mList.get(position));
        holder.img.setImageDrawable(imgList.get(position));
        holder.btn = btnList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class NestedViewHolder extends RecyclerView.ViewHolder
    {
        private TextView mTv;
        private ImageView img;
        private CardView btn;
        public NestedViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.nestedItemTv);
            img = itemView.findViewById(R.id.subcategory_imageview);
            btn = itemView.findViewById(R.id.apply_subcategory);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(btn.getContext(),mTv.getText().toString() + "'apply btn", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
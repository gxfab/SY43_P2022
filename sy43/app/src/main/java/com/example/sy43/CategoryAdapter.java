package com.example.sy43;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    List <DataModel2> mList;
    private List<String> list = new ArrayList<>();
    private List<String> amountList = new ArrayList<>();

    public CategoryAdapter(List<DataModel2> mList){
        this.mList = mList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.category , parent , false);
        return new CategoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        DataModel2 model = mList.get(position);
        holder.mTextView.setText(model.getCategoryTitle());

        holder.mTextView2.setText(model.getCategoryTotalAmount());

        boolean isExpandable = model.isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        if (isExpandable){
            holder.mArrowImage.setImageResource(R.drawable.arrow_up);
        }else{
            holder.mArrowImage.setImageResource(R.drawable.arrow_down);
        }

        holder.category_imageview.setImageDrawable(model.getCategoryImg());

        SubcategoryAdapter adapter = new SubcategoryAdapter(list,amountList, position);
        holder.nestedRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.nestedRecyclerView.setHasFixedSize(true);
        holder.nestedRecyclerView.setAdapter(adapter);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setExpandable(!model.isExpandable());
                list = model.getSubcategoriesTitles();
                amountList = model.getSubcategoriesAmounts();
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout linearLayout;
        private RelativeLayout expandableLayout;
        private TextView mTextView;
        private ImageView mArrowImage;
        private ImageView category_imageview;
        private RecyclerView nestedRecyclerView;
        private TextView mTextView2;

        public CategoryViewHolder(@NonNull View itemView){
            super(itemView);

            linearLayout = itemView.findViewById(R.id.category_linear_layout);
            expandableLayout = itemView.findViewById(R.id.category_expandable_layout);
            mTextView = itemView.findViewById(R.id.categoryTV);
            mArrowImage = itemView.findViewById(R.id.arro_imageview);
            nestedRecyclerView = itemView.findViewById(R.id.subcategory_RV);
            category_imageview = itemView.findViewById(R.id.category_IV);
            mTextView2 = itemView.findViewById(R.id.totalcategoryexpense);

        }

    }

}
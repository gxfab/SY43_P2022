package com.example.sy43;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sy43.database.AppDatabase;
import com.example.sy43.database.Category;
import com.example.sy43.database.SubCategory;

import java.util.List;

public class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.NestedViewHolder> {

    private List<String> mList;
    private List<Drawable> imgList;
    private List<CardView> btnList;
    private List<EditText> etList;
    private int position;

    public NestedAdapter(List<String> mList, List<Drawable> imgList, List<CardView> btnList, List<EditText> etList, int position){
        this.mList = mList;
        this.imgList = imgList;
        this.btnList = btnList;
        this.etList = etList;
        this.position = position;
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
        holder.et.setText("");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class NestedViewHolder extends RecyclerView.ViewHolder {
        private TextView mTv;
        private ImageView img;
        private CardView btn;
        private EditText et;
        private String category;
        public NestedViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.nestedItemTv);
            img = itemView.findViewById(R.id.subcategory_imageview);
            btn = itemView.findViewById(R.id.apply_subcategory);
            et = itemView.findViewById(R.id.expected_expense);




            DataModel datamodel = ItemAdapter.mList.get(position);
            category = datamodel.getItemText();
            //btn.setVisibility(View.INVISIBLE);

            AppDatabase db = AppDatabase.getInstance(itemView.getContext());

            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
//                    Toast.makeText(btn.getContext(),mTv.getText().toString() + "'s expected amount is " + et.getText().toString(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(btn.getContext(),mTv.getText().toString() + "'apply btn", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(btn.getContext(),mTv.getText().toString() + " in " + datamodel.getItemText(), Toast.LENGTH_SHORT).show();
                    /*int catID = 0;

                    if (db.categoryDao().findByName(category) == null){
                        db.categoryDao().insertAll(new Category(category));
                    }
                    catID = db.categoryDao().findByName(category).getId();
                    db.subCategoryDao().insertAll(new SubCategory(mTv.getText().toString(), Double.parseDouble(et.getText().toString()),catID));

                    btn.setVisibility(View.INVISIBLE);*/
                    if(et.isEnabled()){
                        if(!et.getText().toString().matches("")){
                            et.setEnabled(false);
                            int catID = 0;

                            if (db.categoryDao().findByName(category) == null){
                                db.categoryDao().insertAll(new Category(category));
                            }
                            catID = db.categoryDao().findByName(category).getId();
                            db.subCategoryDao().insertAll(new SubCategory(mTv.getText().toString(), Double.parseDouble(et.getText().toString()),catID));

                            btn.setVisibility(View.INVISIBLE);
                            Toast.makeText(btn.getContext(),"Envelope Saved !", Toast.LENGTH_SHORT).show();
                        }

                    }

                    if(!et.isEnabled()){
                        Toast.makeText(btn.getContext(),"Already Saved !", Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }

        @NonNull
        public Lifecycle getLifecycle() {
            return null;
        }
    }
}














package com.example.sy43;

import android.content.ClipData;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sy43.database.AppDatabase;
import com.example.sy43.database.Category;
import com.example.sy43.database.SubCategory;

import java.util.List;

public class AddSubcategoryAdapter extends RecyclerView.Adapter<AddSubcategoryAdapter.NestedViewHolder> {

    private static List<String> mList;
    private List<CardView> btnList;
    private List<EditText> etList;
    private int position;

    public AddSubcategoryAdapter(List<String> mList, List<CardView> btnList, List<EditText> etList, int position){
        this.mList = mList;
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
        holder.btn = btnList.get(position);
        holder.et.setText("");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class NestedViewHolder extends RecyclerView.ViewHolder {
        private TextView mTv;
        private CardView btn;
        private EditText et;
        private String category;
        public NestedViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.nestedItemTv);
            btn = itemView.findViewById(R.id.apply_subcategory);
            et = itemView.findViewById(R.id.expected_expense);




            DataModel3 datamodel = AddCategoryAdapter.mList.get(position);
            category = datamodel.getItemText();
            //btn.setVisibility(View.INVISIBLE);

            AppDatabase db = AppDatabase.getInstance(itemView.getContext());

            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
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

                    else if(!et.isEnabled()){
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














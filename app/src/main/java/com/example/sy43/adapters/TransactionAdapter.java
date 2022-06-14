package com.example.sy43.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.sy43.R;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.SubCategory;
import com.example.sy43.db.entity.Transaction;
import com.example.sy43.viewmodels.CategoryViewModel;
import com.example.sy43.viewmodels.SubCategoryViewModel;
import com.example.sy43.viewmodels.TransactionViewModel;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransactionAdapter extends ArrayAdapter<Transaction> {

    private TransactionViewModel transVM;
    private Context context;
    private int resource;
    private SubCategoryViewModel subCatVM;
    private CategoryViewModel catVM;
    private LifecycleOwner owner;

    public TransactionAdapter(@NonNull Context context, LifecycleOwner owner, int resource, List<Transaction> trans, TransactionViewModel transViewModel, CategoryViewModel catVM, SubCategoryViewModel subcatVM) {
        super(context, resource, trans);
        this.context = context;
        this.transVM = transViewModel;
        this.subCatVM = subcatVM;
        this.catVM = catVM;
        this.resource = resource;
        this.owner = owner;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(resource, null);
        }

        Transaction trans = getItem(position);

        if (trans != null) {
            TextView price = v.findViewById(R.id.tvPrice);
            TextView tdate = v.findViewById(R.id.tvDate);
            TextView tcategory = v.findViewById(R.id.tvCategory);
            TextView tsubCategory = v.findViewById(R.id.tvSubCategory);
            catVM.getCategoryById(trans.getCategory()).observe(owner, new Observer<Categorydb>() {
                        @Override
                        public void onChanged(Categorydb categorydb) {
                            tcategory.setText(categorydb.getCatName());
                        }
                    }
            );
            subCatVM.getSubCategoryById(trans.getSubCategory()).observe(owner, new Observer<SubCategory>() {
                        @Override
                        public void onChanged(SubCategory subcategorydb) {
                            tsubCategory.setText(subcategorydb.getSubCatName());
                        }
                    }
            );

            price.setText("$" + trans.getValue());
            Date date = new Date(trans.getDate());
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = formatter.format(date);
            tdate.setText(s);
            CardView categoryCard = v.findViewById(R.id.categoryCard);
            categoryCard.setCardBackgroundColor(position % 2 == 0 ? Color.argb(196, 196, 196, 255) : Color.argb(232, 232, 232, 255));
        }

        return v;
    }
}

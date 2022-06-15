package com.example.sy43.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.sy43.CategoryActivity;
import com.example.sy43.CategoryDetailsActivity;
import com.example.sy43.MainActivity;
import com.example.sy43.ObjectiveDetailsActivity;
import com.example.sy43.R;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.SubCategory;
import com.example.sy43.db.entity.Transaction;
import com.example.sy43.viewmodels.SubCategoryViewModel;
import com.example.sy43.viewmodels.TransactionViewModel;

import java.util.List;

public class SubCategoryAdapter extends ArrayAdapter<SubCategory> {
    private int resourceLayout;
    private Context mContext;
    private SubCategoryViewModel subCatVM;
    private TransactionViewModel transVM;
    private LifecycleOwner owner;

    public SubCategoryAdapter(@NonNull Context context, LifecycleOwner owner, int resource, List<SubCategory> categories, SubCategoryViewModel subCatVM, TransactionViewModel transVM) {
        super(context, resource, categories);
        this.resourceLayout = resource;
        this.mContext = context;
        this.subCatVM = subCatVM;
        this.transVM = transVM;
        this.owner = owner;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        SubCategory category = getItem(position);

        if (category != null) {
            TextView price = v.findViewById(R.id.tvCurrentPrice);
            TextView name = v.findViewById(R.id.tvName);
            ProgressBar progressBar = v.findViewById(R.id.progressBar);
            progressBar.setMax((int) category.getMaxValue());
            progressBar.setProgress((int) category.CurrentValue(), true);
            name.setText(category.getSubCatName());
            float montant = 0;
            transVM.getTransactionsFromSubCat(category.getSubCatID()).observe(owner, new Observer<List<Transaction>>() {
                @Override
                public void onChanged(List<Transaction> receivedTransactions) {
                    float montant = 0;
                    for (Transaction trans : receivedTransactions) {
                        montant += trans.getValue();
                    }
                    price.setText("$" + montant + "/$" + category.getMaxValue());
                    category.setCurrentValue(montant);
                    progressBar.setProgress((int) category.CurrentValue(), true);
                    progressBar.getProgressDrawable().setColorFilter(
                            category.CurrentValue()<category.getMaxValue()? Color.GREEN:Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                }
            });

            //price.setText("$" + montant + "/$" + category.getMaxValue());
            Button del = v.findViewById(R.id.deleteButton);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subCatVM.delSubCategories(category.getSubCatID());
                    CharSequence text = "Deleted";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(MainActivity.getAppContext(), text, duration);
                    toast.show();
                    Intent intent = new Intent(v.getContext(), CategoryActivity.class);
                    v.getContext().startActivity(intent);
                }
            });

        }

        return v;
    }



}

package com.example.sy43.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sy43.R;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.Transaction;
import com.example.sy43.viewmodels.CategoryViewModel;
import com.example.sy43.viewmodels.TransactionViewModel;

import java.util.List;

public class TransactionAdapter extends ArrayAdapter<Transaction> {

    private TransactionViewModel transVM;
    private Context context;
    private int resource;

    public TransactionAdapter(@NonNull Context context, int resource, List<Transaction> trans, TransactionViewModel transViewModel) {
        super(context, resource, trans);
        this.context = context;
        this.transVM = transViewModel;
        this.resource = resource;
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
            /* TODO
            TextView price = v.findViewById(R.id.tvCurrentPrice);
            TextView name = v.findViewById(R.id.tvName);*/
        }

        return v;
    }
}

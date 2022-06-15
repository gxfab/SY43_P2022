package com.example.sy43;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.sy43.adapters.CategoryAdapter;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.Transaction;
import com.example.sy43.db.mainDB.DB;
import com.example.sy43.db.mainDB.DBexec;
import com.example.sy43.repositories.CategoryRepository;
import com.example.sy43.viewmodels.CategoryViewModel;
import com.example.sy43.viewmodels.SubCategoryViewModel;
import com.example.sy43.viewmodels.TransactionViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class graphActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);
        PieChart chart = (PieChart) findViewById(R.id.graph);
        ArrayList<PieEntry> entries = new ArrayList<>();
        CategoryViewModel categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.init();

        TransactionViewModel transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.init();

        SubCategoryViewModel subCategoryViewModel = new ViewModelProvider(this).get(SubCategoryViewModel.class);
        subCategoryViewModel.init();

        LifecycleOwner owner = this;
        categoryViewModel.getCategories().observe(this, new Observer<List<Categorydb>>() {
            @Override
            public void onChanged(List<Categorydb> receivedCategories) {
                for (int i = 0; i < receivedCategories.size(); i++) {
                    entries.add(new PieEntry(receivedCategories.get(i).CurrentValue(), receivedCategories.get(i).getCatName()));
                }
                PieDataSet set = new PieDataSet(entries, "Election Results");
                PieData data = new PieData(set);
                chart.setData(data);
                chart.invalidate();
                ;

            }
        });



    }

}

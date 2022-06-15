package com.example.sy43;

import android.util.Log;

import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.Transaction;
import com.example.sy43.db.mainDB.DB;
import com.example.sy43.db.mainDB.DBexec;
import com.example.sy43.repositories.CategoryRepository;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class graphActivity extends AppCompatActivity{
    private DB db;
    private DBexec databaseExecutor;
    PieChart chart = (PieChart) findViewById(R.id.graph);
    ArrayList<PieEntry> entries = new ArrayList<>();

    public void onCreate() {
        Log.d("Test", "aled");
        Futures.addCallback(
                db.CategoryDAO().findCategories(),
                new FutureCallback<List<Categorydb>>() {
                    public void onSuccess(List<Categorydb> result) {
                        ;
                        for (int i = 0; i < result.size(); i++) {
                            entries.add(new PieEntry(result.get(i).CurrentValue(), result.get(i).getCatName()));
                        }
                        PieDataSet set = new PieDataSet(entries, "Election Results");
                        PieData data = new PieData(set);
                        chart.setData(data);
                        chart.invalidate();
                    }

                    public void onFailure(@NonNull Throwable thrown) {
                        Log.d("Test", String.valueOf(thrown));
                    }
                },
                //MainActivity.getAppContext().getMainExecutor()
                databaseExecutor
        );
    }

}

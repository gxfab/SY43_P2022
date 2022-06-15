package com.example.sy43;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.Transaction;
import com.example.sy43.db.mainDB.DB;
import com.example.sy43.db.mainDB.DBexec;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

import java.util.Calendar;
import java.util.TimeZone;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.context = getApplicationContext();
        DB db = DB.getAppDatabase(getAppContext());
        DBexec databaseExecutor = DBexec.getExecutor();
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        setContentView(R.layout.activity_home);

        final CardView cardView = findViewById(R.id.category);
        cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CategoryActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        final CardView cardSummary = findViewById(R.id.summaryCard);
        cardSummary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TransactionSummary.class);
                v.getContext().startActivity(intent);
            }
        });

        final CardView cardAdd = findViewById(R.id.addCard);
        cardAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        final CardView cardPreview = findViewById(R.id.preview);
        cardPreview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), graphActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }
}
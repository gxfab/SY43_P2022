package com.example.sy43;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sy43.db.DAO.CategoryDAO;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.mainDB.DB;
import com.example.sy43.db.mainDB.DBexec;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.context = getApplicationContext();
        DB db = DB.getAppDatabase(getAppContext());
        DBexec databaseExecutor = DBexec.getExecutor();

        Categorydb category2 = new Categorydb();
        category2.setCatName("test2");

        Futures.addCallback(
                db.CategoryDAO().insert(category2),
                new FutureCallback<Void>() {
                    @Override
                    public void onSuccess(Void stp) {
                        Log.d("Test", "Insert done");
                    }

                    public void onFailure(@NonNull Throwable thrown) {
                        Log.d("Test", String.valueOf(thrown));
                    }
                },
                databaseExecutor
        );





        Futures.addCallback(
                db.CategoryDAO().findAll(),
                new FutureCallback<List<Categorydb>>() {
                    public void onSuccess(List<Categorydb> result) {
                        Log.d("Test", "Result"+result.size());
                        Log.d("Test", "Result"+result.get(0).getCatName());

                    }

                    public void onFailure(@NonNull Throwable thrown) {
                        Log.d("Test", "Test2");
                    }
                },
                databaseExecutor
        );


        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.category);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CategoryActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }
}
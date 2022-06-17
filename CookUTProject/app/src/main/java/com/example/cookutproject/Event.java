package com.example.cookutproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cookutproject.data.CookUTViewModel;
import com.example.cookutproject.data.adapters.EventAdapter;

public class Event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        RecyclerView recyclerView = findViewById(R.id.recyclerviewSemestre);
        EventAdapter adapter = new EventAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CookUTViewModel cookUTViewModel = new ViewModelProvider(this).get(CookUTViewModel.class);

        cookUTViewModel.getReadAllSemestre().observe(this,s ->{
            adapter.setSemestreList(s);
        });

    }
    public void changeActivityMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

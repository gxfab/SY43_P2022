package com.example.nomoola.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Transaction;

import com.example.nomoola.R;
import com.example.nomoola.adapter.CategoryAdapter;
import com.example.nomoola.fragment.CategoryFragment;
import com.example.nomoola.viewModel.CategoryViewModel;

public class CategoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("CREATION", "onCreate from " + this.getClass().toString() + " started");
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_category);

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.fragment_container, new CategoryFragment());
        trans.addToBackStack(null);
        trans.commit();

        Log.d("CREATION", "onCreate from " + this.getClass().toString() + " finished");
    }
}

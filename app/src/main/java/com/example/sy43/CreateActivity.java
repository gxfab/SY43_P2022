package com.example.sy43;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.viewmodels.CategoryViewModel;

public class CreateActivity  extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        CategoryViewModel categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel .class);
        categoryViewModel.init();

        Button cat = (Button) findViewById(R.id.btnCatConfirm);
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorydb cat = new Categorydb();
                TextView name = (TextView) findViewById(R.id.txtCategory);
                cat.setCatName(name.getText().toString());
                cat.setMaxValue(50);
                cat.setCurrentValue(5);
                cat.setObjective(false);
                categoryViewModel.createCategory(cat);
                Intent intent = new Intent(v.getContext(), CategoryActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        Button obj = (Button) findViewById(R.id.btnObjConfirm);
        obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categorydb cat = new Categorydb();
                TextView name = (TextView) findViewById(R.id.objName);
                cat.setCatName(name.getText().toString());
                cat.setMaxValue(50);
                cat.setCurrentValue(5);
                cat.setObjective(true);
                categoryViewModel.createCategory(cat);
                Intent intent = new Intent(v.getContext(), CategoryActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }


}

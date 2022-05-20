package com.example.zeroday.views.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.zeroday.R;
import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.IncomesCategory;
import com.example.zeroday.repositories.IncomesCategoryRepository;

import java.util.List;

public class IncomesTest extends AppCompatActivity {
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_incomes_test);
        // show category list fragmnt
        setContentView(R.layout.activity_incomes_test);
        db  = new DbHelper(this);
        IncomesCategoryRepository incomesCategoryRepository = new IncomesCategoryRepository(db.getWritableDatabase());
        incomesCategoryRepository.createDefaultIncomesCategory();

        List<IncomesCategory> incomesCategoryList = incomesCategoryRepository.findAll();

            Log.i("Test", incomesCategoryList.toString());


    }


    @Override
    protected void onDestroy() {
        this.db.close();
        super.onDestroy();
    }
}
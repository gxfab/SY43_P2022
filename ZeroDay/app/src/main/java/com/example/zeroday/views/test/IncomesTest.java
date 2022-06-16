package com.example.zeroday.views.test;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

import com.aspose.cells.Workbook;
import com.example.zeroday.R;
import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.IncomeCategory;
import com.example.zeroday.repositories.IncomeCategoryRepository;
import com.example.zeroday.services.IncomeCategoryService;

public class IncomesTest extends AppCompatActivity {
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_incomes_test);
        // show category list fragmnt
        setContentView(R.layout.activity_incomes_test);

        IncomeCategoryService incomeCategoryService = new IncomeCategoryService(this);
        incomeCategoryService.save(new IncomeCategory("Salary", "Salary"));
        incomeCategoryService.save(new IncomeCategory("Bonus", "Bonus"));
        incomeCategoryService.save(new IncomeCategory("Other", "Other"));

        List<IncomeCategory> incomeCategoryList = incomeCategoryService.getAll();
        Workbook workbook = new Workbook();

// Add value in the cell
        workbook.getWorksheets().get(0).getCells().get("A1").putValue("Hello World!");

// Save as Excel XLSX file
        try {
            workbook.save("Excel.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Log.i("Test", incomeCategoryList.toString());

    }


    @Override
    protected void onDestroy() {
        this.db.close();
        super.onDestroy();
    }
}
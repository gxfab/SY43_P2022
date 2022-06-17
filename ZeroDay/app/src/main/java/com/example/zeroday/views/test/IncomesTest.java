package com.example.zeroday.views.test;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

//import com.aspose.cells.Workbook;
import com.example.zeroday.R;
import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.Budget;
import com.example.zeroday.models.ExpenseCategory;
import com.example.zeroday.models.Frequency;
import com.example.zeroday.models.Income;
import com.example.zeroday.models.IncomeCategory;
import com.example.zeroday.repositories.IncomeCategoryRepository;
import com.example.zeroday.seeders.ExpenseCategorySeeder;
import com.example.zeroday.seeders.IncomeCategorySeeder;
import com.example.zeroday.services.BudgetService;
import com.example.zeroday.services.ExpenseCategoryService;
import com.example.zeroday.services.IncomeCategoryService;
import com.example.zeroday.services.IncomeService;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class IncomesTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_incomes_test);
        // show category list fragmnt
        setContentView(R.layout.activity_incomes_test);

        IncomeCategoryService incomeCategoryService = new IncomeCategoryService(this);
//        incomeCategoryService.deleteAll();
        ExpenseCategoryService expenseCategoryService = new ExpenseCategoryService(this);
//        expenseCategoryService.deleteAll();
        new IncomeCategorySeeder(this).run();
        new ExpenseCategorySeeder(this).run();
        Workbook workbook = new HSSFWorkbook();
//        new ExpenseCategoryService(this).create(new ExpenseCategory(""));
        List<IncomeCategory> incomeCategoryList = incomeCategoryService.getAll();
        List<ExpenseCategory> expenseCategories = expenseCategoryService.getAll();
//        Workbook workbook = new Workbook();
        // Create a budget object
        BudgetService budgetService = new BudgetService(this);
        budgetService.create(new Budget("budget-001", "18-06-2022", "18-07-2022", Frequency.MONTHLY));
        Budget myBudget = budgetService.findOneByCode("budget-001");

        budgetService.addIncome(myBudget.getCodeBudget(), new Income(Frequency.MONTHLY, (long) 500.0, "Salaire", incomeCategoryService.findOneByCode("cat-inc-sal")));
        

// Add value in the cell
//        workbook.getWorksheets().get(0).getCells().get("A1").putValue("Hello World!");

// Save as Excel XLSX file
//        try {
//            workbook.save("Excel.xlsx");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Log.i("Test", incomeCategoryList.toString());
        Log.i("Test", expenseCategories.toString());
        Log.i("Test", budgetService.getAll().toString());
        Log.i("Test", new IncomeService(this).getAll().toString());
        Log.i("Test", myBudget.toString());

    }


    @Override
    protected void onDestroy() {
//        this.db.close();
        super.onDestroy();
    }
}
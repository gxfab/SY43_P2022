package com.example.sy43;

import static com.example.sy43.MainActivity.getAppContext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ListView;

import com.example.sy43.adapters.CategoryAdapter;
import com.example.sy43.adapters.TransactionAdapter;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.Transaction;
import com.example.sy43.db.mainDB.DB;
import com.example.sy43.db.mainDB.DBexec;
import com.example.sy43.viewmodels.CategoryViewModel;
import com.example.sy43.viewmodels.SubCategoryViewModel;
import com.example.sy43.viewmodels.TransactionViewModel;

import java.util.Collections;
import java.util.List;
/**
 * Sommaire des dépenses par catégorie, mensuellement
 */
public class TransactionSummary extends AppCompatActivity {

    private TransactionViewModel transVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB db = DB.getAppDatabase(getAppContext());
        DBexec databaseExecutor = DBexec.getExecutor();
        setContentView(R.layout.summary_transaction);
    }

    @Override
    protected void onResume() {
        super.onResume();
        transVM = new ViewModelProvider(this).get(TransactionViewModel.class);
        transVM.init();
        CategoryViewModel categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.init();
        SubCategoryViewModel subCategoryViewModel = new ViewModelProvider(this).get(SubCategoryViewModel.class);
        subCategoryViewModel.init();
        LifecycleOwner owner = this;

        transVM.getTransactions().observe(this, new Observer<List<Transaction>>() {

            /**
             *
             * @param receivedTransactions les transactions ( dépenses par sous-catégorie ) à afficher
             */
            @Override
            public void onChanged(List<Transaction> receivedTransactions) {
                // https://stackoverflow.com/questions/5070830/populating-a-listview-using-an-arraylist
                Collections.reverse(receivedTransactions);
                TransactionAdapter transArrayAdapter = new TransactionAdapter(
                        TransactionSummary.this,
                        owner,
                        R.layout.transaction_list_item,
                        receivedTransactions,
                        transVM, categoryViewModel, subCategoryViewModel);
                ListView catLv = (ListView) findViewById(R.id.categoryListView);
                catLv.setAdapter(transArrayAdapter);
            }
        });



    }
}
package com.example.sy43;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sy43.database.AppDatabase;
import com.example.sy43.database.Category;
import com.example.sy43.database.SubCategory;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class add_expense extends Fragment {
    protected EditText et_name, et_amount;
    private String date;
    protected Button addBtn;
    private TabLayout tabLayout;
    private Spinner catSpinner, subcatSpinner;
    private ViewPager2 viewPager2;
    private RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);
        //recyclerView = view.findViewById(R.id.latest_expenses_rv);
        et_amount = view.findViewById(R.id.add_expense_amount);
        et_name = view.findViewById(R.id.add_expense_name);
        addBtn= view.findViewById(R.id.add_expense_btn);
        tabLayout = view.findViewById(R.id.tabLayout);
        catSpinner = view.findViewById(R.id.add_expense_category);
        subcatSpinner = view.findViewById(R.id.add_expense_subcategory);

        AppDatabase db = AppDatabase.getInstance(view.getContext());

        List<String> categories = new ArrayList<String>();
        for (Category cat : db.categoryDao().getAll()) {
            categories.add(cat.getName());
        }
        ArrayAdapter<String> catData = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, categories);
        catData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(catData);

        catSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                 @Override
                                                 public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                     String catText = catSpinner.getSelectedItem().toString();
                                                     List<String> subcategories = new ArrayList<String>();
                                                     for (SubCategory subcat : db.subCategoryDao().findByCategory(db.categoryDao().findByName(catText).id)) {
                                                         subcategories.add(subcat.getName());
                                                     }
                                                     ArrayAdapter<String> subcatData = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, subcategories);
                                                     subcatData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                     subcatSpinner.setAdapter(subcatData);
                                                 }

                                                 @Override
                                                 public void onNothingSelected(AdapterView<?> adapterView) {

                                                 }
                                             }
        );


//        List<String> subcategories = new ArrayList<String>();
//        for (SubCategory subcat : db.subCategoryDao().findByCategory(db.categoryDao().findByName(catText).id)) {
//            subcategories.add(subcat.getName());
//        }
//        ArrayAdapter<String> subcatData = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, subcategories);
//        subcatData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        subcatSpinner.setAdapter(subcatData);

        date="1106JUNE";

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Name", et_name.getText().toString());
                bundle.putString("Amount", et_amount.getText().toString());
                bundle.putString("Date", date);

                latest_expenses latestExpenses = new latest_expenses();
                latestExpenses.setArguments(bundle);

                TabLayout.Tab tab = tabLayout.getTabAt(1);
                tab.select();
            }
        });


        return view;
    }
}
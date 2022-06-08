package com.example.sy43;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sy43.database.AppDatabase;
import com.example.sy43.database.Category;
import com.example.sy43.database.Expenses;
import com.example.sy43.database.MonthlyRevenue;
import com.example.sy43.database.SubCategory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add_expense#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_expense extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText expensename;
    private EditText expenseamount;
    private Button addexpense;

    public add_expense() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_expense.
     */
    // TODO: Rename and change types and number of parameters
    public static add_expense newInstance(String param1, String param2) {
        add_expense fragment = new add_expense();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);
        // Inflate the layout for this fragment

        // Test to add an expense to the database by retrieving data from text inputs and using fake categories (which works)

//        expensename = view.findViewById(R.id.add_expense_name);
//        expenseamount = view.findViewById(R.id.add_expense_amount);
//        addexpense = view.findViewById(R.id.add_expense_btn);

//        AppDatabase db = AppDatabase.getInstance(getContext());
//        db.categoryDao().insertAll(new Category("Housing"));
//        db.subCategoryDao().insertAll(new SubCategory("Rent",100.00,db.categoryDao().getAll().get(0).id));
//        db.monthlyRevenueDao().insertAll(new MonthlyRevenue(500.00,1));
//
//        addexpense.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                db.expensesDao().insertAll(new Expenses(expensename.getText().toString(),Double.parseDouble(expenseamount.getText().toString()),db.subCategoryDao().getAll().get(0).id,db.monthlyRevenueDao().getAll().get(0).id));
//            }
//        });
        return view;
    }
}